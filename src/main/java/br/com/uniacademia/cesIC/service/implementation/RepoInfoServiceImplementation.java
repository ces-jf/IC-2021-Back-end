package br.com.uniacademia.cesIC.service.implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.uniacademia.cesIC.dto.repo.RepoFDTO;
import br.com.uniacademia.cesIC.dto.repo.RepoHDTO;
import br.com.uniacademia.cesIC.endpoints.RepoEndPoint;
import br.com.uniacademia.cesIC.endpoints.UserInfooEndPoint;
import br.com.uniacademia.cesIC.exception.repo.notFound.RepoNotFoundException;
import br.com.uniacademia.cesIC.models.Contributor;
import br.com.uniacademia.cesIC.models.RepoInfo;
import br.com.uniacademia.cesIC.repositors.RepositoryRepoInfo;
import br.com.uniacademia.cesIC.service.ContributoService;
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
    UserInfooEndPoint userInfooEndPoint;

    @Autowired
    ExportService exportService;

    @Autowired
    ContributoService contributoService;

    @Autowired
    ModelMapper mapper;

    @Override
    public RepoFDTO findByName(String name) {
	log.info("Start - RepoInfoServiceImplementation.findByName - Name - {}", name);

	Optional<RepoInfo> optRepoInfo = repositoryRepoInfo.findByName(name);
	if (!optRepoInfo.isPresent()) {
	    throw new RepoNotFoundException();
	}
	RepoFDTO repoFDTO = this.mapper.map(optRepoInfo.get(), RepoFDTO.class);

	log.info("Start - RepoInfoServiceImplementation.findByName - RepoFDTO - {}", repoFDTO);
	return repoFDTO;
    }

    @Override
    public RepoFDTO include(RepoHDTO repoHDTO) {
	log.info("Start - RepoInfoServiceImplementation.include - Repository - {}", repoHDTO.getRepo());

	boolean cont = true;
	int page = 0;
	List<Contributor> contributors = new ArrayList<>();

	Optional<RepoInfo> repoInfo = this.repositoryRepoInfo
		.findByFullName(repoHDTO.getUser() + "/" + repoHDTO.getRepo());

	if (repoInfo.isEmpty()) {
	    repoInfo = this.repoEndPoint.findRepoInfo(repoHDTO.getUser(), repoHDTO.getRepo());
	    if (!repoInfo.isPresent()) {
		throw new RepoNotFoundException();
	    }
	}

	if (repoInfo.get().getContributors().isEmpty()) {
	    do {
		List<Contributor> auxList = new ArrayList<>();
		auxList = this.repoEndPoint.findAllContributors(repoHDTO.getUser(), repoHDTO.getRepo(), page++);
		if (!auxList.isEmpty())
		    contributors.addAll(auxList);
		else
		    cont = false;

	    } while (cont);
	    repoInfo.get().setContributors(contributors);
	}

	RepoInfo repo = this.repositoryRepoInfo.save(repoInfo.get());
	RepoFDTO repoFDTO = this.mapper.map(repo, RepoFDTO.class);

	log.info("End - RepoInfoServiceImplementation.include - Repository - {}", repoFDTO);
	return repoFDTO;
    }

    @Override
    public List<RepoInfo> findAll() {
	log.info("Start - RepoInfoServiceImplementation.findAll");

	List<RepoInfo> repoInfos = repositoryRepoInfo.findAll();

	log.info("End - RepoInfoServiceImplementation.findAll");
	return repoInfos;
    }

//	@Override
//	public void exportar(RepoFDTO repoFDTO) {
//		log.info("Start - RepoInfoServiceImplementation.exportar");
//
//		String path = "src/main/resources/csv/repo.csv";
//		this.exportService.exportarCSV(Arrays.asList(repoFDTO), path);
//
//		log.info("End - RepoInfoServiceImplementation.exportar");
//	}

}
