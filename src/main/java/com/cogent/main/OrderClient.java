package com.cogent.main;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "Order", url = "localhost:8085/orders")
public interface OrderClient 
{
	@DeleteMapping("/delete/{userId}")
	public boolean deleteOrdersByUserId(@PathVariable int userId, @RequestHeader("Authorization") String token);
}
