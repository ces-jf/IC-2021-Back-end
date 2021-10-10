package br.com.uniacademia.cesIC.endpoints;

import java.util.List;
import java.util.Optional;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.uniacademia.cesIC.dto.getTags.GetTagsFDTO;
import br.com.uniacademia.cesIC.exception.repo.notFound.RepoNotFoundException;
import br.com.uniacademia.cesIC.models.RepoInfo;
import br.com.uniacademia.cesIC.models.UserRepo;

@FeignClient(value = "repo", url = "${client.port.baseUrl}", decode404 = true, fallback = RepoNotFoundException.class)
public interface RepoEndPoint {

	@GetMapping(produces = "application/json", value = "/repos/{user}/{repo}/contributors?per_page=100&page={pages}")
	List<UserRepo> buscarUsers(@RequestParam("user") String user, @RequestParam("repo") String repo,
			@RequestParam("pages") int pages);

	@GetMapping(produces = "application/json", value = "/repos/{user}/{repo}")
	Optional<RepoInfo> buscarRepoInfo(@RequestParam("user") String user, @RequestParam("repo") String repo);

	@GetMapping(produces = "application/json", value = "/repos/{user}/{repo}/tags?per_page=100&page={pages}")
	List<GetTagsFDTO> buscarTags(@RequestParam("user") String user, @RequestParam("repo") String repo,
			@RequestParam("pages") int pages);

}
