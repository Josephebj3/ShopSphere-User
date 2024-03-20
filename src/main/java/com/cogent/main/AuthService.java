package com.cogent.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService 
{

	@Autowired
	private UserEntityRepository repository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private JwtService jwtService;
	
	public boolean saveUser(UserEntity userEntity)
	{
		if(repository.findAll().isEmpty()) userEntity.setAdmin(true);
		
		userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
		repository.save(userEntity);
		return true;
	}
	
	public boolean updateUserJwtToken(String email, String jwtToken) 
	{
		UserEntity userEntity = repository.findByEmail(email).get();
		if(userEntity == null) return false;
		userEntity.setJwtToken(jwtToken);
		repository.save(userEntity);
		return true;
	}
	
	public String generateToken(String userName)
	{
		return jwtService.generateToken(userName);
	}
	
	
	public boolean validateToken(String token)
	{
		return jwtService.validateToken(token);
	}
	
	public boolean validateUserToken(int userId, String token) 
	{
		return jwtService.validateUserToken(userId, token);
	}
	
	public boolean validateAdminToken(String token)
	{
		return jwtService.validateAdminToken(token);
	}

	public boolean deleteToken(String token) 
	{
		String jwtToken = token.split(" ")[1];
		token = jwtToken;
		
		
		
		UserEntity userEntity = repository.findByJwtToken(token).get();
		if(userEntity == null) return false;
		userEntity.setJwtToken(null);
		repository.save(userEntity);
		return true;
	}

	public int getIdByEmail(String email) 
	{
		return repository.findByEmail(email).get().getId();
	}


	
	
}
