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
import org.springframework.web.bind.annotation.RestController;

import br.com.uniacademia.cesIC.dto.userInfoo.UserInfooDTO;
import br.com.uniacademia.cesIC.dto.userInfoo.UserInfooHDTO;
import br.com.uniacademia.cesIC.models.UserInfoo;
import br.com.uniacademia.cesIC.service.UserRepoService;
import br.com.uniacademia.cesIC.service.implementation.UserInfooServiceImplementation;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping(value = "/userInfo")
public class ControllerUserInfoo {

    @Autowired
    UserInfooServiceImplementation userInfooService;

    @Autowired
    UserRepoService userService;

    @CrossOrigin
    @PostMapping
    @CacheEvict(value = "userInfo", allEntries = true)
    public ResponseEntity<UserInfooDTO> include(@RequestBody @Valid UserInfooHDTO userInfooHDTO) {
	log.info("Start - ControllerUserInfoo.include - UserInfooHDTO - {}", userInfooHDTO);

	UserInfooDTO userInfooDTO = userInfooService.buscarUserInfoGitHub(userInfooHDTO.getUser());

	log.info("End - ControllerUserInfoo.include - UserInfooHDTO");
	return ResponseEntity.ok(userInfooDTO);
    }

    @GetMapping("/exporta")
    public void buscar() {
	List<UserInfoo> userInfos = userInfooService.findAll();
	if (userInfos != null) {
	    userInfooService.exportCSV(userInfos);
	}
    }

    @CrossOrigin
    @Cacheable("userInfo")
    @GetMapping
    public ResponseEntity<List<UserInfoo>> buscarAll() {
	List<UserInfoo> userInfos = userInfooService.findAll();
	if (userInfos != null)
	    return ResponseEntity.ok(userInfos);
	return ResponseEntity.badRequest().build();
    }
}
