package br.com.uniacademia.cesIC.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @CrossOrigin
    @PostMapping
    @CacheEvict(value = "repos", allEntries = true)
    public ResponseEntity<RepoFDTO> include(@RequestBody @Valid RepoHDTO repoHDTO) {
	log.info("Start - ControllerRepo.include - RepoHDTO - {}", repoHDTO);

	RepoFDTO repoFDTO = this.repoInfoService.include(repoHDTO);

	log.info("End - ControllerRepo.include - RepoHDTO - {}", repoHDTO);
	return ResponseEntity.ok(repoFDTO);
    }

    @CrossOrigin
    @GetMapping(params = "repo")
    public ResponseEntity<RepoFDTO> buscarPorRepository(@RequestParam String repo) {
	log.info("Start - ControllerRepo.buscarPorRepository - Repo - {}", repo);

	RepoFDTO repoFDTO = repoInfoService.findByName(repo);

	log.info("End - ControllerRepo.buscarPorRepository - RepoFDTO - {}", repoFDTO);
	return ResponseEntity.ok(repoFDTO);
    }

    @CrossOrigin
    @Cacheable("repos")
    @GetMapping
    public List<RepoInfo> buscarRepository() {
	return this.repoInfoService.findAll();
    }
}
