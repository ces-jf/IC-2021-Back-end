package br.com.uniacademia.cesIC.controllers;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.node.ObjectNode;

import br.com.uniacademia.cesIC.models.RepoInfo;
import br.com.uniacademia.cesIC.repositors.RepositoryRepoInfo;

@RestController
@RequestMapping(value = "/repos")
public class ControllerItem {

	@Autowired
	RepositoryRepoInfo repositoryRepoInfo;

	@PostMapping
	public ResponseEntity<RepoInfo> buscarRepos(@RequestBody ObjectNode obj) {
		if (!obj.equals(null)) {
			RepoInfo repoInfo = new RepoInfo();
			String user = obj.get("user").asText();
			String repos = obj.get("repos").asText();
			RestTemplate restTemplate = new RestTemplate();
			String uri = "https://api.github.com/repos/{user}/{repos}";
			Map<String, String> params = new HashMap<String, String>();
			params.put("user", user);
			params.put("repos", repos);
			repoInfo = restTemplate.getForObject(uri, RepoInfo.class, params);
			repositoryRepoInfo.save(repoInfo);
			return ResponseEntity.ok(repoInfo);
		}
		return ResponseEntity.notFound().build();
	}

	@GetMapping(value = "/{user}/{repos}")
	public ResponseEntity<RepoInfo> buscarPorRepository(@PathVariable String user, @PathVariable String repos) {
		String full_name = user + "/" + repos;
		RepoInfo repoInfo = repositoryRepoInfo.findByPorNome(full_name);
		if (!repoInfo.equals(null)) {
			return ResponseEntity.ok(repoInfo);
		}
		return ResponseEntity.notFound().build();
	}

	@GetMapping
	public List<RepoInfo> buscarRepository() {
		List<RepoInfo> repoInfo = repositoryRepoInfo.findAll();
		return repoInfo;
	}

}
