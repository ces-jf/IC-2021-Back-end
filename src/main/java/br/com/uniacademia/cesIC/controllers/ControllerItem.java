package br.com.uniacademia.cesIC.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
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
import br.com.uniacademia.cesIC.models.User;
import br.com.uniacademia.cesIC.repositors.RepositoryRepoInfo;

@RestController
@RequestMapping(value = "/repos")
public class ControllerItem {

	@Autowired
	RepositoryRepoInfo repositoryRepoInfo;



	@PostMapping
	public ResponseEntity<RepoInfo> buscarRepos(@RequestBody ObjectNode obj) {
		if (!obj.equals(null)) {
			List<User> userList = new ArrayList<>();
			String user = obj.get("user").asText();
			String repos = obj.get("repos").asText();
			RepoInfo repoInfo = new RepoInfo();
			Optional<RepoInfo> repoOptional = repositoryRepoInfo.findByPorNome(repos);
			if (repoOptional.isPresent()) {
				repoInfo = repoOptional.get();
			}

			RestTemplate restTemplate = new RestTemplate();
			String uri = "https://api.github.com/repos/{user}/{repos}";
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
			Map<String, String> params = new HashMap<String, String>();
			params.put("user", user);
			params.put("repos", repos);
			repoInfo = restTemplate.getForObject(uri, RepoInfo.class, params);
			repoInfo.setUsList(userList);
			repositoryRepoInfo.save(repoInfo);
			return ResponseEntity.ok(repoInfo);
		}
		return ResponseEntity.notFound().build();
	}

	@PostMapping(value = "/{user}")
	public ResponseEntity<RepoInfo> buscarPorRepository(@PathVariable String repos) {
		String name = repos;
		Optional<RepoInfo> repoInfo = repositoryRepoInfo.findByPorNome(name);
		if (repoInfo.isPresent()) {
			RepoInfo repo = repoInfo.get();
			return ResponseEntity.ok(repo);
		}
		return ResponseEntity.notFound().build();
	}

	@GetMapping
	public List<RepoInfo> buscarRepository() {
		List<RepoInfo> repoInfo = repositoryRepoInfo.findAll();
		return repoInfo;
	}

	
}
