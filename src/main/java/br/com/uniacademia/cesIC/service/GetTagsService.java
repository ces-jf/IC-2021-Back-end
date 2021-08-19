package br.com.uniacademia.cesIC.service;

import java.util.List;
import java.util.Set;

import br.com.uniacademia.cesIC.dto.getTags.GetTagsFDTO;
import br.com.uniacademia.cesIC.dto.repo.RepoHDTO;
import br.com.uniacademia.cesIC.models.GetTags;

public interface GetTagsService {

	List<GetTags> findAll();

	Set<GetTagsFDTO> buscarTags(RepoHDTO repoHDTO);

	void saveAll(List<GetTags> tags);

}
