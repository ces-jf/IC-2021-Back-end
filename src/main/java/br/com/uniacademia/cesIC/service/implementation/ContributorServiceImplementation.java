package br.com.uniacademia.cesIC.service.implementation;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.uniacademia.cesIC.dto.repo.RepoHDTO;
import br.com.uniacademia.cesIC.dto.userInfoo.CommitFDTO;
import br.com.uniacademia.cesIC.endpoints.ContributorEndPoint;
import br.com.uniacademia.cesIC.models.Contributor;
import br.com.uniacademia.cesIC.repositors.RepositoryContributor;
import br.com.uniacademia.cesIC.service.ContributoService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ContributorServiceImplementation implements ContributoService {

    @Autowired
    RepositoryContributor repositoryContributor;

    @Autowired
    ContributorEndPoint contributorEndPoint;

    @Autowired
    ModelMapper mapper;

    @Override
    public List<Contributor> include(RepoHDTO repoHDTO) {
	log.info("Start - ContributorServiceImplementation.include - Repositiry - {}", repoHDTO);

	List<Contributor> contributors = new ArrayList<>();

//	int page = 1;
//	do {
//	    contributors.addAll(this.contributorEndPoint.buscarContributor(page));
//	    page++;
//	} while (!this.contributorEndPoint.buscarContributor(page).isEmpty());
//
//
//	contributors.stream()
//		.forEach(contributo -> contributo.setNameRepository(repoHDTO.getUser() + "/" + repoHDTO.getRepo()));

	List<CommitFDTO> commitFDTOs = new ArrayList<>();
	int pag = 1;
	do {
	    commitFDTOs.addAll(this.contributorEndPoint.buscarCommits(pag));
	    pag++;
	} while (!this.contributorEndPoint.buscarCommits(pag).isEmpty());

	commitFDTOs.stream().forEach(System.out::println);

	log.info("End - RepoInfoServiceImplementation.include - Repositiry - {}");
	return contributors;
    }
}
