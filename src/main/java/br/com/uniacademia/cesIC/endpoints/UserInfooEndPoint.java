package br.com.uniacademia.cesIC.endpoints;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.uniacademia.cesIC.dto.userRepo.UserRepoFDTO;

@FeignClient(value = "user", url = "${client.port.baseUrl}", decode404 = true)
public interface UserInfooEndPoint {

	@GetMapping(produces = "application/json", value = "/users/{user}")
	UserRepoFDTO getUserInfoo(@RequestParam("user") String user);
	
}
