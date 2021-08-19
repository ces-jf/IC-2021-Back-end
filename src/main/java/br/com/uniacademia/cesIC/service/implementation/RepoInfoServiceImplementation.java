package br.com.uniacademia.cesIC.service.implementation;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.uniacademia.cesIC.dto.repo.RepoFDTO;
import br.com.uniacademia.cesIC.dto.repo.RepoHDTO;
import br.com.uniacademia.cesIC.endpoints.RepoEndPoint;
import br.com.uniacademia.cesIC.exception.repo.notFound.RepoNotFoundException;
import br.com.uniacademia.cesIC.models.RepoInfo;
import br.com.uniacademia.cesIC.repositors.RepositoryRepoInfo;
import br.com.uniacademia.cesIC.service.ExportService;
import br.com.uniacademia.cesIC.service.RepoInfoService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class RepoInfoServiceImplementation implements RepoInfoService {

	@Autowired
	RepositoryRepoInfo repositoryRepoInfo;

	@Autowired
	RepoEndPoint repoEndPoint;

	@Autowired
	ExportService exportService;

	@Autowired
	ModelMapper mapper;

	@Override
	public RepoFDTO findByName(String name) {
		log.info("Start - RepoInfoServiceImplementation.findByName - Name - {}", name);

		Optional<RepoInfo> optRepoInfo = repositoryRepoInfo.findByName(name);
		if (!optRepoInfo.isPresent()) {
			throw new RepoNotFoundException();
		}
		RepoFDTO repoFDTO = mapper.map(optRepoInfo.get(), RepoFDTO.class);

		log.info("Start - RepoInfoServiceImplementation.findByName - RepoFDTO - {}", repoFDTO);
		return repoFDTO;
	}

	@Override
	public RepoFDTO include(RepoHDTO repoHDTO) {
		log.info("Start - RepoInfoServiceImplementation.include - Repositiry - {}", repoHDTO.getRepo());

		RepoInfo repoInfo = this.repoEndPoint.buscarRepoInfo(repoHDTO.getUser(), repoHDTO.getRepo());
		if (repoInfo != null) {
			repositoryRepoInfo.save(repoInfo);
		}

		log.info("End - RepoInfoServiceImplementation.include - Repositiry - {}", repoHDTO.getRepo());
		return mapper.map(repoInfo, RepoFDTO.class);
	}

	@Override
	public List<RepoInfo> findAll() {
		log.info("Start - RepoInfoServiceImplementation.findAll");

		log.info("End - RepoInfoServiceImplementation.findAll");
		return repositoryRepoInfo.findAll();
	}

	@Override
	public void exportar(RepoFDTO repoFDTO) {
		log.info("Start - RepoInfoServiceImplementation.exportar");
		
		String path = "src/main/resources/csv/repo.csv";
		this.exportService.exportarCSV(Arrays.asList(repoFDTO), path);
		
		log.info("End - RepoInfoServiceImplementation.exportar");
	}

}
