package br.com.uniacademia.cesIC.endpoints;

import java.util.List;
import java.util.Optional;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.uniacademia.cesIC.dto.getTags.GetTagsFDTO;
import br.com.uniacademia.cesIC.exception.repo.notFound.RepoNotFoundException;
import br.com.uniacademia.cesIC.models.RepoInfo;
import br.com.uniacademia.cesIC.models.User;

@FeignClient(value = "repo", url = "https://api.github.com/repos/", decode404 = true, fallback = RepoNotFoundException.class)
public interface RepoEndPoint {

	@GetMapping(produces = "application/json", value = "/{user}/{repo}/contributors?per_page=100&page={pages}")
	List<User> buscarUsers(@RequestParam("user") String user, @RequestParam("repo") String repo,
			@RequestParam("pages") int pages);

	@GetMapping(produces = "application/json", value = "/{user}/{repo}")
	@ExceptionHandler
	Optional<RepoInfo> buscarRepoInfo(@RequestParam("user") String user, @RequestParam("repo") String repo);

	@GetMapping(produces = "application/json", value = "/{user}/{repo}/tags?per_page=100&page={pages}")
	List<GetTagsFDTO> buscarTags(@RequestParam("user") String user, @RequestParam("repo") String repo,
			@RequestParam("pages") int pages);

}
