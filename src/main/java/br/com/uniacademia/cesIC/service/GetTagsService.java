package br.com.uniacademia.cesIC.service;

import java.util.List;

import br.com.uniacademia.cesIC.models.GetTags;

public interface GetTagsService {

	List<GetTags> findAll();

	List<GetTags> buscarTags(String user, String repos);
	
	void saveAll(List<GetTags> tags);

}
