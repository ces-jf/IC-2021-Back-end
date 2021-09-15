package br.com.uniacademia.cesIC.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.uniacademia.cesIC.dto.LoginDTO;
import br.com.uniacademia.cesIC.dto.token.TokenFRDTO;
import br.com.uniacademia.cesIC.dto.user.UserPDTO;
import br.com.uniacademia.cesIC.service.SecurityService;
import br.com.uniacademia.cesIC.service.UserService;
import br.com.uniacademia.cesIC.util.Response;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@NoArgsConstructor
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private SecurityService securityService;

	@PostMapping("/common")
	public ResponseEntity<Response<TokenFRDTO>> includeVoluntary(@RequestBody @Valid UserPDTO userPDTO) {
		log.info("Start - UserController.includeVoluntary - UserPDTO: {}", userPDTO);
		Response<TokenFRDTO> response = new Response<>();

		LoginDTO login = this.userService.includeCommon(userPDTO);
		TokenFRDTO token = this.securityService.login(login);
		response.setData(token);

		log.info("End - UserController.includeVoluntary - TokenFRDTO: {}", token);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
	
	@Secured("ADMINISTRATOR")
	@PostMapping("/administrator")
	public ResponseEntity<Response<TokenFRDTO>> includeAdministrator(@RequestBody @Valid UserPDTO userPDTO) {
		log.info("Start - UserController.includeAdministrator - UserPDTO: {}", userPDTO);
		Response<TokenFRDTO> response = new Response<>();

		LoginDTO login = this.userService.includeAdministrator(userPDTO);
		TokenFRDTO token = this.securityService.login(login);
		response.setData(token);

		log.info("End - UserController.includeAdministrator - TokenFRDTO: {}", token);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
}
