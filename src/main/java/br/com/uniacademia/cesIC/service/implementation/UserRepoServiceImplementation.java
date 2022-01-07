package br.com.uniacademia.cesIC.service.implementation;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.uniacademia.cesIC.dto.userRepo.UserRepoFDTO;
import br.com.uniacademia.cesIC.dto.userRepo.UserRepoHDTO;
import br.com.uniacademia.cesIC.endpoints.RepoEndPoint;
import br.com.uniacademia.cesIC.exception.userInfo.notFound.UserInfoNotFoundException;
import br.com.uniacademia.cesIC.models.RepoInfo;
import br.com.uniacademia.cesIC.models.UserRepo;
import br.com.uniacademia.cesIC.repositors.RepositoryUserRepo;
import br.com.uniacademia.cesIC.service.ExportService;
import br.com.uniacademia.cesIC.service.UserRepoService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserRepoServiceImplementation implements UserRepoService {

    @Autowired
    RepositoryUserRepo repositoryUser;

    @Autowired
    ModelMapper mapper;

    @Autowired
    RepoEndPoint repoEndPoint;

    @Autowired
    ExportService exportService;

    @Override
    public void save(UserRepo user) {
	log.info("Start - UserRepoServiceImplementation.save");

	if (user == null)
	    throw new UserInfoNotFoundException();
	repositoryUser.save(user);

	log.info("End - UserRepoServiceImplementation.save");
    }

    @Override
    public List<UserRepoFDTO> findAll() {
	log.info("Start - UserRepoServiceImplementation.findAll");
	List<UserRepo> userRepos = repositoryUser.findAll();
	if (userRepos.isEmpty())
	    throw new UserInfoNotFoundException();
	List<UserRepoFDTO> userRepoFDTOs = userRepos.stream()
		.map(userRpo -> this.mapper.map(userRepos, UserRepoFDTO.class)).collect(Collectors.toList());
	log.info("End - UserRepoServiceImplementation.findAll");
	return userRepoFDTOs;
    }

    @Override
    public UserRepoFDTO findByLogin(String login) {
	log.info("Start - UserRepoServiceImplementation.findByLogin - Login - {}", login);

	Optional<UserRepo> userOPT = repositoryUser.findByLogin(login);
	if (!userOPT.isPresent()) {
	    throw new UserInfoNotFoundException();
	}

	log.info("End - UserRepoServiceImplementation.findByLogin ");
	return this.mapper.map(userOPT.get(), UserRepoFDTO.class);
    }

    @Override
    public List<UserRepoFDTO> buscarContributors(UserRepoHDTO userHDTO) {
	log.info("Start - UserRepoServiceImplementation.buscarContributors - UserRepoHDTO - {}", userHDTO);
	Set<UserRepoFDTO> userList = new HashSet<>();

	userList.addAll(findAll());

	Optional<RepoInfo> repoInfo = this.repoEndPoint.findRepoInfo(userHDTO.getUser(), userHDTO.getRepo());
	if (!repoInfo.isPresent())
	    throw new UserInfoNotFoundException();

	boolean buscarUsres = true;
	int page = 1;
	while (buscarUsres) {

	    List<UserRepo> users = this.repoEndPoint.buscarUsers(userHDTO.getUser(), userHDTO.getRepo(), page);
	    if (users.isEmpty())
		buscarUsres = false;
	    else
		userList.addAll(users.stream().map(user -> this.mapper.map(user, UserRepoFDTO.class))
			.collect(Collectors.toList()));
	    page++;
	}

	saveAll(userList.stream().map(user -> this.mapper.map(user, UserRepo.class)).collect(Collectors.toList()));
	this.exportService.exportarCSV(userList.stream().collect(Collectors.toList()),
		"src/main/resources/csv/user.csv");

	log.info("End - UserRepoServiceImplementation.buscarContributors -  UserRepoFDTO");
	return userList.stream().map(user -> mapper.map(user, UserRepoFDTO.class)).collect(Collectors.toList());
    }

    @Override
    public void saveAll(List<UserRepo> userList) {
	log.info("Start - UserRepoServiceImplementation.saveAll");

	repositoryUser.saveAll(userList);

	log.info("End - UserRepoServiceImplementation.saveAll");
    }

}
