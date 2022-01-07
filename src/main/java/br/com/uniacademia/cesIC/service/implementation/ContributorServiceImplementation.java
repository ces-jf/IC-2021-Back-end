package br.com.uniacademia.cesIC.service.implementation;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.node.ObjectNode;

import br.com.uniacademia.cesIC.dto.userInfoo.CommitDTO;
import br.com.uniacademia.cesIC.endpoints.ContributorEndPoint;
import br.com.uniacademia.cesIC.models.Commit;
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
    public List<Contributor> include(List<Contributor> contributors) {
	log.info("Start - ContributorServiceImplementation.include - Repository - {}", contributors);

	int pag = 1;
	List<ObjectNode> json = Arrays.asList();
	List<Commit> commits = Arrays.asList();

	for (Contributor contributor : contributors) {
	    do {
		json.addAll(this.contributorEndPoint.findCommits(pag, contributor.getLogin()));
	    } while (!this.contributorEndPoint.findCommits(pag++, contributor.getLogin()).isEmpty());
	    pag = 0;

	    for (ObjectNode obj : json) {
		CommitDTO commit = new CommitDTO();
		String date = obj.path("commit").path("author").path("date").asText();

		commit.setDate(LocalDate.parse(date, DateTimeFormatter.ISO_DATE_TIME));

		commits.add(this.mapper.map(commit, Commit.class));
	    }
	    contributor.setListCommits(commits);
	}

	log.info("End - RepoInfoServiceImplementation.include - Repository - {}");
	return contributors;
    }
}
