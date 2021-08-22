package br.com.uniacademia.cesIC.endpoints;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.uniacademia.cesIC.dto.user.UserFDTO;

@FeignClient(value = "user", url = "https://api.github.com/users/", decode404 = true)
public interface UserInfooEndPoint {

	@GetMapping(produces = "application/json", value = "/{user}")
	UserFDTO getUserInfoo(@RequestParam("user") String user);
	
}
