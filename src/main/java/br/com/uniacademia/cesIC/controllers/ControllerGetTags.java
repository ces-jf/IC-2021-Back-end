package br.com.uniacademia.cesIC.controllers;

import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.uniacademia.cesIC.dto.getTags.GetTagsFDTO;
import br.com.uniacademia.cesIC.dto.repo.RepoHDTO;
import br.com.uniacademia.cesIC.service.GetTagsService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value = "/getTags")
public class ControllerGetTags {

	@Autowired
	GetTagsService getTagsService;

	@PostMapping
	public ResponseEntity<Set<GetTagsFDTO>> buscarGetTags(@RequestBody @Valid RepoHDTO repoHDTO) {
		log.info("Start - ControllerGetTags.buscarGetTags- RepoHDTO - {}", repoHDTO);
		
		Set<GetTagsFDTO> tagsFDTOs = getTagsService.buscarTags(repoHDTO);
		
		log.info("End - ControllerGetTags.buscarGetTags- GetTagsFDTO - {}", tagsFDTOs);
		return ResponseEntity.ok(tagsFDTOs);
	}
}
