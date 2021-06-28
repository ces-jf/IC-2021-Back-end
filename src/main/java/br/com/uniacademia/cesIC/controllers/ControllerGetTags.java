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

import br.com.uniacademia.cesIC.models.GetTags;
import br.com.uniacademia.cesIC.repositors.RepositoryGetTags;

@RestController
@RequestMapping(value = "/GetTags")
public class ControllerGetTags {

	@Autowired
	RepositoryGetTags repositoruGetTags;

	@PostMapping
	public ResponseEntity<Set<GetTags>> buscarGetTags(@RequestBody ObjectNode data) {
		if (data.isEmpty()) {
			return ResponseEntity.badRequest().build();
		}
		RestTemplate restTemplate = new RestTemplate();
		String user = data.get("user").asText();
		String repos = data.get("repos").asText();
		Set<GetTags> tagsList = new HashSet<GetTags>();
		tagsList.addAll(repositoruGetTags.findAll());

		boolean buscarUsres = true;
		int page = 1;
		while (buscarUsres) {
			String uri1 = "https://api.github.com/repos/" + user + "/" + repos + "/tags?per_page=100&page=" + page;
			ResponseEntity<List<GetTags>> users = restTemplate.exchange(uri1, HttpMethod.GET, null,
					new ParameterizedTypeReference<List<GetTags>>() {
					});
			if (users.getBody().isEmpty())
				buscarUsres = false;
			else
				tagsList.addAll(users.getBody());
			page++;
		}
		return ResponseEntity.ok(tagsList);
	}
}
