package br.com.uniacademia.cesIC.service;

import java.util.List;

import br.com.uniacademia.cesIC.dto.repo.RepoFDTO;
import br.com.uniacademia.cesIC.dto.repo.RepoHDTO;
import br.com.uniacademia.cesIC.models.RepoInfo;

public interface RepoInfoService {

	RepoFDTO findByName(String name);
	
	RepoFDTO include(RepoHDTO repoHDTO);

	List<RepoInfo> findAll();
	
//	void exportar(RepoFDTO repoFDTO);
}
