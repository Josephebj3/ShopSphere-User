package com.cogent.main;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
public class UserDao implements UserDetails 
{
	private int id;
	private String email;
	private String userName;
	private String password;
	private Name name;
	private Address address;
	private String phone;
	private boolean admin;
	private String jwtToken;

	public UserDao(UserEntity userEntity) 
	{
		email = userEntity.getEmail();
		userName = userEntity.getUserName();
		password = userEntity.getPassword();
		name.setFirstName(userEntity.getName().getFirstName());
		name.setLastName(userEntity.getName().getLastName());
		address.setCity(userEntity.getAddress().getCity());
		address.setStreet(userEntity.getAddress().getStreet());
		address.setNumber(userEntity.getAddress().getNumber());
		address.setZipCode(userEntity.getAddress().getZipCode());
		phone = userEntity.getPhone();
		admin = userEntity.isAdmin();
		jwtToken = userEntity.getJwtToken();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return email;
	}
	
	public String getUserName() 
	{
		return userName;
	}
	

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
