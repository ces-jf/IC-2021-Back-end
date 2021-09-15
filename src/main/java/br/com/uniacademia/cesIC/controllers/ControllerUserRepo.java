package br.com.uniacademia.cesIC.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.uniacademia.cesIC.dto.userRepo.UserRepoFDTO;
import br.com.uniacademia.cesIC.dto.userRepo.UserRepoHDTO;
import br.com.uniacademia.cesIC.endpoints.UserInfooEndPoint;
import br.com.uniacademia.cesIC.service.UserRepoService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping(value = "/userRepo")
public class ControllerUserRepo {

	@Autowired
	UserRepoService userServiceImp;

	@Autowired
	UserInfooEndPoint userInfooEndPoint;

	@CrossOrigin
	@PostMapping
	public ResponseEntity<List<UserRepoFDTO>> include(@RequestBody @Valid UserRepoHDTO userHDTO) {
		log.info("Start ControllerUserRepo.buscarUser - UserRepo - {}", userHDTO.getUser());

		List<UserRepoFDTO> userFDTOs = userServiceImp.buscarContributors(userHDTO);

		log.info("End - ControllerUserRepo.buscarUser - UserRepoHDTO - UserRepo - {}", userHDTO.getUser());
		return ResponseEntity.ok(userFDTOs);
	}
	
	@CrossOrigin
	@GetMapping(params = "user")
	public UserRepoFDTO buscarUsuario(@RequestParam String user) {
		log.info("Start - ControllerUserRepo.buscarUsuario - UserRepo - {}", user);
		
		log.info("End - ControllerUserRepo.buscarUsuario - UserRepo - {}", user);
		return this.userServiceImp.findByLogin(user);
		
	}

}
