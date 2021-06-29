package br.com.uniacademia.cesIC.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.node.ObjectNode;

import br.com.uniacademia.cesIC.models.GetTags;
import br.com.uniacademia.cesIC.service.GetTagsService;

@RestController
@RequestMapping(value = "/getTags")
public class ControllerGetTags {

	@Autowired
	GetTagsService getTagsService;

	@PostMapping
	public ResponseEntity<List<GetTags>> buscarGetTags(@RequestBody ObjectNode data) {
		if (data.isEmpty()) {
			return ResponseEntity.badRequest().build();
		}
		String user = data.get("user").asText();
		String repos = data.get("repos").asText();
		List<GetTags> tagsList = getTagsService.buscarTags(user, repos);
		return ResponseEntity.ok(tagsList);
	}
}
