package br.com.uniacademia.cesIC.controllers;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.node.ObjectNode;

import br.com.uniacademia.cesIC.models.User;
import br.com.uniacademia.cesIC.repositors.RepositoryUser;

@RestController
@RequestMapping(value = "/User")
public class ControllerUser {

	@Autowired
	RepositoryUser repositoryUser;

	@PostMapping("/User")
	public Object buscarUser(@RequestBody ObjectNode login) {
		RestTemplate restTemplate = new RestTemplate();
		String user = login.get("user").asText();
		String repos = login.get("repos").asText();
		Set<User> userList = new HashSet<User>();

		userList.addAll(repositoryUser.findAll());

		boolean buscarUsres = true;
		int page = 1;
		while (buscarUsres) {
			String uri2 = "https://api.github.com/repos/" + user + "/" + repos + "/contributors?per_page=100&page="
					+ page;
			ResponseEntity<List<User>> users = restTemplate.exchange(uri2, HttpMethod.GET, null,
					new ParameterizedTypeReference<List<User>>() {
					});
			if (users.getBody().isEmpty())
				buscarUsres = false;
			else
				userList.addAll(users.getBody());
			page++;
		}
		repositoryUser.saveAll(userList);
		return userList;
	}

}
