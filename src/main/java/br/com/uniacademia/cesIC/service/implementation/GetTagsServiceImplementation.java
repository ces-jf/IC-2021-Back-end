package br.com.uniacademia.cesIC.service.implementation;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.uniacademia.cesIC.dto.getTags.GetTagsFDTO;
import br.com.uniacademia.cesIC.dto.repo.RepoHDTO;
import br.com.uniacademia.cesIC.endpoints.RepoEndPoint;
import br.com.uniacademia.cesIC.exception.repo.notFound.RepoNotFoundException;
import br.com.uniacademia.cesIC.models.GetTags;
import br.com.uniacademia.cesIC.models.RepoInfo;
import br.com.uniacademia.cesIC.repositors.RepositoryGetTags;
import br.com.uniacademia.cesIC.service.GetTagsService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class GetTagsServiceImplementation implements GetTagsService {

	@Autowired
	RepositoryGetTags repositoryGetTags;

	@Autowired
	RepoEndPoint getTagsEndPoint;

	@Autowired
	ModelMapper mapper;

	@Override
	public List<GetTags> findAll() {
		return repositoryGetTags.findAll();
	}

	@Override
	public Set<GetTagsFDTO> buscarTags(RepoHDTO repoHDTO) {

		Optional<RepoInfo> repoInfo = this.getTagsEndPoint.buscarRepoInfo(repoHDTO.getUser(), repoHDTO.getRepo());
		if (!repoInfo.isPresent()) {
			throw new RepoNotFoundException();
		}

		Set<GetTagsFDTO> tagsList = new HashSet<>();
		tagsList.addAll(findAll().stream().map(tag -> mapper.map(tag, GetTagsFDTO.class)).collect(Collectors.toSet()));
		boolean buscarUsres = true;
		int page = 1;
		while (buscarUsres) {

			List<GetTagsFDTO> getTagsFDTOs = this.getTagsEndPoint.buscarTags(repoHDTO.getUser(), repoHDTO.getRepo(),
					page);

			if (getTagsFDTOs.isEmpty())
				buscarUsres = false;
			else
				tagsList.addAll(getTagsFDTOs);
			page++;
		}
		List<GetTags> getTags = tagsList.stream().map(tag -> mapper.map(tag, GetTags.class))
				.collect(Collectors.toList());
		saveAll(getTags);
//		exportCSV(getTags);
		return tagsList;
	}

	@Override
	public void saveAll(List<GetTags> tags) {
		repositoryGetTags.saveAll(tags);

	}

//	private void exportCSV(List<GetTags> tags) {
//		try {
//			Writer writer = Files.newBufferedWriter(Paths.get("src/main/resources/csv/getTags.csv"));
//			StatefulBeanToCsv<GetTags> beanToCsv = new StatefulBeanToCsvBuilder<GetTags>(writer).build();
//			beanToCsv.write(tags);
//			writer.flush();
//			writer.close();
//		} catch (IOException | CsvDataTypeMismatchException | CsvRequiredFieldEmptyException e) {
//			log.error("Erro ao exportar", e);
//		}
//
//	}

}
