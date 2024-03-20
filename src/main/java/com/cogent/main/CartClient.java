package com.cogent.main;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;




@FeignClient(name = "Cart", url = "localhost:8083/cart")
public interface CartClient 
{
	@DeleteMapping("/{userId}/remove")
	public boolean deleteCartByUserId(@PathVariable int userId, @RequestHeader("Authorization") String token);
	
	
	
}
