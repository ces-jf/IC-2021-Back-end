package br.com.uniacademia.cesIC.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.node.ObjectNode;

import br.com.uniacademia.cesIC.service.UserService;

@RestController
@RequestMapping(value = "/user")
public class ControllerUser {

	@Autowired
	UserService userServiceImp;

	@PostMapping
	public Object buscarUser(@RequestBody ObjectNode login) {
		String user = login.get("user").asText();
		String repos = login.get("repos").asText();

		return userServiceImp.buscarContributors(user, repos);
	}

}
