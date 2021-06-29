package br.com.uniacademia.cesIC.service.implementation;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.uniacademia.cesIC.config.RestTemplateConfig;
import br.com.uniacademia.cesIC.models.GetTags;
import br.com.uniacademia.cesIC.repositors.RepositoryGetTags;
import br.com.uniacademia.cesIC.service.GetTagsService;

@Service
public class GetTagsServiceImplemntation implements GetTagsService {

	@Autowired
	RepositoryGetTags repositoryGetTags;

	@Autowired
	RestTemplateConfig restTemplate;

	@Override
	public List<GetTags> findAll() {
		return repositoryGetTags.findAll();
	}

	@Override
	public List<GetTags> buscarTags(String user, String repos) {
		Set<GetTags> tagsList = new HashSet<GetTags>();
		tagsList.addAll(findAll());
		boolean buscarUsres = true;
		int page = 1;
		while (buscarUsres) {
			String uri1 = "https://api.github.com/repos/" + user + "/" + repos + "/tags?per_page=100&page=" + page;
			ResponseEntity<List<GetTags>> users = restTemplate.restTemplate().exchange(uri1, HttpMethod.GET, null,
					new ParameterizedTypeReference<List<GetTags>>() {
					});
			if (users.getBody().isEmpty())
				buscarUsres = false;
			else
				tagsList.addAll(users.getBody());
			page++;
		}
		List<GetTags> tags = new ArrayList<GetTags>();
		tags.addAll(tagsList);
		saveAll(tags);
		return tags;
	}

	@Override
	public void saveAll(List<GetTags> tags) {
		repositoryGetTags.saveAll(tags);

	}

}
