package com.cogent.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserDaoService implements UserDetailsService
{

	@Autowired
	private UserEntityRepository repository;
	
	public UserDao loadUserByEmail(String email) throws UsernameNotFoundException 
	{
		UserEntity data	=repository.findByEmail(email).orElseThrow(() ->new UsernameNotFoundException("User not Found"));
	
		return UserDao.builder()
				.email(data.getEmail())
				.userName(data.getUserName())
				.password(data.getPassword())
				.name(Name.builder()
						.firstName(data.getName().getFirstName())
						.lastName(data.getName().getLastName())
						.build())
				.address(Address.builder()
						.city(data.getAddress().getCity())
						.street(data.getAddress().getStreet())
						.number(data.getAddress().getNumber())
						.zipCode(data.getAddress().getZipCode())
						.build())
				.phone(data.getPhone())
				.admin(data.isAdmin())
				.jwtToken(data.getJwtToken())
				.build();
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEntity data	=repository.findByEmail(username).orElseThrow(() ->new UsernameNotFoundException("User not Found"));
		
		return UserDao.builder()
				.email(data.getEmail())
				.userName(data.getUserName())
				.password(data.getPassword())
				.name(Name.builder()
						.firstName(data.getName().getFirstName())
						.lastName(data.getName().getLastName())
						.build())
				.address(Address.builder()
						.city(data.getAddress().getCity())
						.street(data.getAddress().getStreet())
						.number(data.getAddress().getNumber())
						.zipCode(data.getAddress().getZipCode())
						.build())
				.phone(data.getPhone())
				.admin(data.isAdmin())
				.jwtToken(data.getJwtToken())
				.build();
	}

	public UserDao loadUserByJwtToken(String jwtToken) 
	{
		UserEntity data	=repository.findByJwtToken(jwtToken).orElseThrow(() ->new UsernameNotFoundException("User not Found"));
		
		return UserDao.builder()
				.email(data.getEmail())
				.userName(data.getUserName())
				.password(data.getPassword())
				.name(Name.builder()
						.firstName(data.getName().getFirstName())
						.lastName(data.getName().getLastName())
						.build())
				.address(Address.builder()
						.city(data.getAddress().getCity())
						.street(data.getAddress().getStreet())
						.number(data.getAddress().getNumber())
						.zipCode(data.getAddress().getZipCode())
						.build())
				.phone(data.getPhone())
				.admin(data.isAdmin())
				.jwtToken(data.getJwtToken())
				.build();
	}
	
	public String getUserEmailByUserId(int userId)
	{
		return repository.findById(userId).get().getEmail();
	}
}























