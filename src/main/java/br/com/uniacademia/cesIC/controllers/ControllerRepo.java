package br.com.uniacademia.cesIC.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.uniacademia.cesIC.dto.repo.RepoFDTO;
import br.com.uniacademia.cesIC.dto.repo.RepoHDTO;
import br.com.uniacademia.cesIC.models.RepoInfo;
import br.com.uniacademia.cesIC.service.RepoInfoService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value = "/repos")
public class ControllerRepo {

	@Autowired
	RepoInfoService repoInfoService;

	@PostMapping
	public ResponseEntity<RepoFDTO> include(@RequestBody @Valid RepoHDTO repoHDTO) {
		log.info("Start - ControllerRepo.include - RepoHDTO - {}", repoHDTO);

		RepoFDTO repoFDTO = this.repoInfoService.include(repoHDTO);

		log.info("End - ControllerRepo.include - RepoHDTO - {}", repoHDTO);
		return ResponseEntity.ok(repoFDTO);
	}

	@GetMapping(params = "repo")
	public ResponseEntity<RepoFDTO> buscarPorRepository(@PathVariable String repo) {
		log.info("Start - ControllerRepo.buscarPorRepository - Repo - {}", repo);

		RepoFDTO repoFDTO = repoInfoService.findByName(repo);

		log.info("End - ControllerRepo.buscarPorRepository - RepoFDTO - {}", repoFDTO);
		return ResponseEntity.ok(repoFDTO);
	}

	@GetMapping
	public List<RepoInfo> buscarRepository() {
		return this.repoInfoService.findAll();
	}
}
