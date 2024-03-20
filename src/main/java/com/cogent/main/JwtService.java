package com.cogent.main;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

	private final String secret = "gQ7opnLAA3KvHX7JvkCniaBlkbAU1Qfvzdacp+sfXEFo3n6/pFBNxHFvqyr6BpWm";
	
	@Autowired
	private UserDaoService userDaoService;

	public boolean validateToken(String jwtToken) 
	{
		String token = jwtToken.split(" ")[1];
		jwtToken = token;
		
		Jwts.parser().setSigningKey(getSignKey()).build().parseClaimsJws(jwtToken);
		
		if(Jwts.parser().setSigningKey(getSignKey()).build().parseClaimsJws(jwtToken).getPayload().getExpiration().before(new Date(System.currentTimeMillis()))) return false;                 
		
		return userDaoService.loadUserByJwtToken(jwtToken).getJwtToken().compareTo(jwtToken) == 0;
	}
	
	public boolean validateUserToken(int userId, String jwtToken) 
	{
		String token = jwtToken.split(" ")[1];
		jwtToken = token;
		
		Jwts.parser().setSigningKey(getSignKey()).build().parseClaimsJws(jwtToken);
		
		if(Jwts.parser().setSigningKey(getSignKey()).build().parseClaimsJws(jwtToken).getPayload().getExpiration().before(new Date(System.currentTimeMillis()))) return false;                 
		
		return userDaoService.loadUserByJwtToken(jwtToken).getEmail().compareTo(userDaoService.getUserEmailByUserId(userId)) == 0;
	}
	
	public boolean validateAdminToken(String jwtToken) 
	{
		String token = jwtToken.split(" ")[1];
		jwtToken = token;
		
		Jwts.parser().setSigningKey(getSignKey()).build().parseClaimsJws(jwtToken);
		
		if(Jwts.parser().setSigningKey(getSignKey()).build().parseClaimsJws(jwtToken).getPayload().getExpiration().before(new Date(System.currentTimeMillis()))) return false;
		
		return userDaoService.loadUserByJwtToken(jwtToken).isAdmin();
	}
	
	public String generateToken(String userName)
	{
		return createToken(new HashMap<String, Object>(), userName);
	}

	private String createToken(Map<String, Object> claims, String userName)
	{
		return Jwts.builder()
				.claims(claims)
				.subject(userName)
				.issuedAt(new Date(System.currentTimeMillis()))
				.expiration(new Date(System.currentTimeMillis()+1000*60*30))
				.signWith(getSignKey())
				.compact();
	}

	private Key getSignKey() {
		byte[] keys = Decoders.BASE64.decode(secret);
		return Keys.hmacShaKeyFor(keys);
	}

	
}
