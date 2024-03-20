package com.cogent.main;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/admin")
public class AdminController
{
	
	@Autowired
	private AdminService adminService;
	
	
	
	
	
	@GetMapping("/users")
	public List<UserDao> getUsers(@RequestHeader("Authorization") String token) 
	{
		return adminService.getUsers(token);
	}
	
	@PutMapping("/users/{userId}")
	public UserEntity updateUser(@PathVariable int userId, @RequestBody UserEntity userEntity, @RequestHeader("Authorization") String token) 
	{
		return adminService.updateUser(userId, userEntity, token);
	}
	
	@DeleteMapping("/users/{userId}")
	public String deleteUser(@PathVariable int userId, @RequestHeader("Authorization") String token)
	{
		return adminService.deleteUser(userId, token) ? "User deletion success" : "User deletion failed";
	}
}
