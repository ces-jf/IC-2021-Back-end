package br.com.uniacademia.cesIC.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.uniacademia.cesIC.dto.user.UserFDTO;
import br.com.uniacademia.cesIC.dto.user.UserHDTO;
import br.com.uniacademia.cesIC.endpoints.UserInfooEndPoint;
import br.com.uniacademia.cesIC.service.UserService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value = "/user")
public class ControllerUser {

	@Autowired
	UserService userServiceImp;

	@Autowired
	UserInfooEndPoint userInfooEndPoint;

	@PostMapping
	public ResponseEntity<List<UserFDTO>> include(@RequestBody @Valid UserHDTO userHDTO) {
		log.info("Start ControllerUser.buscarUser - User - {}", userHDTO.getUser());

		List<UserFDTO> userFDTOs = userServiceImp.buscarContributors(userHDTO);

		log.info("End - ControllerUser.buscarUser - UserHDTO - User - {}", userHDTO.getUser());
		return ResponseEntity.ok(userFDTOs);
	}

	@GetMapping(params = "user")
	public UserFDTO buscarUsuario(@RequestParam String user) {
		log.info("Start - ControllerUser.buscarUsuario - User - {}", user);
		
		log.info("End - ControllerUser.buscarUsuario - User - {}", user);
		return this.userServiceImp.findByLogin(user);
		
	}

}
