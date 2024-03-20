package com.cogent.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class UserController 
{

	@Autowired
	private AuthService service;

	@Autowired
	private AuthenticationManager manager;

	@PostMapping("/register")
	public String addUser(@RequestBody UserEntity userEntity) 
	{
		return service.saveUser(userEntity) ? "New User Registered" : "Failed Registration";
	}
	
	private boolean updateUserJwtToken(String email, String jwtToken) 
	{
		return service.updateUserJwtToken(email, jwtToken);
		
	}

	@PostMapping("/login")
	public String generateToken(@RequestBody AuthRequest authRequest) 
	{
		System.out.println(authRequest);
		Authentication auth = manager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
		if (auth.isAuthenticated()) 
		{
			String jwtToken = service.generateToken(authRequest.getUsername());
			updateUserJwtToken(authRequest.getUsername(), jwtToken);
			return jwtToken;
		} 
		else 
		{
			throw new RuntimeException("Invalid");
		}
	}
	
	@PostMapping("/getId")
	public String getIdByEmail(@RequestBody GetIdDao getIdDao) 
	{
		System.out.println(getIdDao.getEmail());
		return String.valueOf(service.getIdByEmail(getIdDao.getEmail()));
	}
	

	@GetMapping("/validate")
	public boolean validToken(@RequestHeader("Authorization") String token)
	{
		return service.validateToken(token);
	}
	
	@GetMapping("/validateUser")
	public boolean validUserToken(@RequestParam int userId, @RequestHeader("Authorization") String token)
	{
		return service.validateUserToken(userId,token);
	}
	
	
	@GetMapping("/validateAdmin")
	public boolean validAdminToken(@RequestHeader("Authorization") String token) 
	{
		return service.validateAdminToken(token);
	}
	
	
	@PostMapping("/logout")
	public String logout(@RequestHeader("Authorization") String token)
	{
		return service.deleteToken(token) ? "Logout Success" : "Logout Failed";
	}

}
