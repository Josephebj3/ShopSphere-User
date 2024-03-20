package com.cogent.main;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService 
{
	@Autowired
	private UserEntityRepository repository;
	
	@Autowired
	private CartClient cartClient;
	
	@Autowired
	private OrderClient orderClient;
	
	@Autowired
	private WishlistClient wishlistClient;
	
	@Autowired
	private JwtService jwtService;
	

	public List<UserDao> getUsers(String token) 
	{
		if(!jwtService.validateAdminToken(token)) return null;
		
		List<UserDao> userDaoList = new LinkedList<UserDao>();
		List<UserEntity> userEntityList = repository.findAll();
		
		for(UserEntity data: userEntityList) 
		{
			userDaoList.add(UserDao.builder()
				.id(data.getId())
				.email(data.getEmail())
				.userName(data.getUserName())
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
				.build());
		}
		System.out.println("email = " + userDaoList.get(0).getEmail());
		System.out.println("userName = " + userDaoList.get(0).getUserName());
		System.out.println("zipcode = " + userDaoList.get(0).getAddress().getZipCode());
		return userDaoList;
	}



	public UserEntity updateUser(int id, UserEntity userEntity, String token) 
	{
		if(!jwtService.validateAdminToken(token)) return null;
		
		userEntity.setId(id);
		userEntity.setPassword(repository.findById(id).get().getPassword());
		userEntity.setEmail(repository.findById(id).get().getEmail());
		
		return repository.save(userEntity);
	}



	public boolean deleteUser(int id, String token) 
	{
		if(!jwtService.validateAdminToken(token)) return false;
		
		if(repository.findById(id).isEmpty()) return false;
		
		cartClient.deleteCartByUserId(id, token);
		orderClient.deleteOrdersByUserId(id, token);
		wishlistClient.deleteByUserId(id, token);
		repository.deleteById(id);
		
		return repository.findById(id).isEmpty();
	}

}
