package com.cogent.main;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "Wishlist", url = "localhost:8084/wishlist")
public interface WishlistClient 
{
	@DeleteMapping("/delete/{userId}")
	public boolean deleteByUserId(@PathVariable int userId, @RequestHeader("Authorization") String token);
}
