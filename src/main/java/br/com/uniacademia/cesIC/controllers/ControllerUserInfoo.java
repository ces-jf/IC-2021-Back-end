package br.com.uniacademia.cesIC.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.node.ObjectNode;

import br.com.uniacademia.cesIC.dto.UserInfooDTO;
import br.com.uniacademia.cesIC.service.implementation.UserInfooServiceImplementation;

@RestController
@RequestMapping(value = "/userInfo")
public class ControllerUserInfoo {

	@Autowired
	UserInfooServiceImplementation userInfooService;

	@PostMapping("/UserInfo")
	public ResponseEntity<UserInfooDTO> buscarUserInfoGitHub(@RequestBody ObjectNode nameUser) {
		if (!nameUser.equals(null) && !nameUser.isEmpty()) {
			String login = nameUser.get("nameUser").asText();

			UserInfooDTO userInfooDTO = userInfooService.buscarUserInfoGitHub(login);
			userInfooService.save(userInfooDTO);
			
			return ResponseEntity.ok(userInfooDTO);
		}
		return ResponseEntity.badRequest().build();
	}
	
	@GetMapping("/{login}")
	public UserInfooDTO buscarUserInfooMongo(@PathVariable String login) {
		return userInfooService.userInfooToUserInfooDTO(userInfooService.findByLogin(login));
	}
}
