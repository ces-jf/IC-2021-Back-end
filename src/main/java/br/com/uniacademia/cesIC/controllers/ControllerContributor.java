package br.com.uniacademia.cesIC.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.uniacademia.cesIC.dto.repo.RepoHDTO;
import br.com.uniacademia.cesIC.models.Contributor;
import br.com.uniacademia.cesIC.service.ContributoService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value = "/contributor")
public class ControllerContributor {

    @Autowired
    private ContributoService contributoService;

    @PostMapping
    public ResponseEntity<List<Contributor>> includ(@RequestBody @Valid RepoHDTO repoHDTO) {
	log.info("Start - ControllerContributor.include - Repository - {}", repoHDTO);

//	List<Contributor> contributors = this.contributoService.include(repoHDTO);

	log.info("End - ControllerContributor.include - Cotributors - {}");

	return ResponseEntity.ok(null);
    }

}
