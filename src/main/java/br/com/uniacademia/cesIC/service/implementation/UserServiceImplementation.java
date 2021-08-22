package br.com.uniacademia.cesIC.service.implementation;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.uniacademia.cesIC.dto.user.UserFDTO;
import br.com.uniacademia.cesIC.dto.user.UserHDTO;
import br.com.uniacademia.cesIC.endpoints.RepoEndPoint;
import br.com.uniacademia.cesIC.exception.repo.notFound.RepoNotFoundException;
import br.com.uniacademia.cesIC.exception.user.notFound.UserInfoNotFoundException;
import br.com.uniacademia.cesIC.models.RepoInfo;
import br.com.uniacademia.cesIC.models.User;
import br.com.uniacademia.cesIC.repositors.RepositoryUser;
import br.com.uniacademia.cesIC.service.ExportService;
import br.com.uniacademia.cesIC.service.UserService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserServiceImplementation implements UserService {

	@Autowired
	RepositoryUser repositoryUser;

	@Autowired
	ModelMapper mapper;

	@Autowired
	RepoEndPoint repoEndPoint;

	@Autowired
	ExportService exportService;

	@Override
	public void save(User user) {
		log.info("Start - UserServiceImplementation.save");

		if (user == null)
			throw new UserInfoNotFoundException();
		repositoryUser.save(user);

		log.info("End - UserServiceImplementation.save");
	}

	@Override
	public List<User> findAll() {
		log.info("Start - UserServiceImplementation.findAll");

		log.info("End - UserServiceImplementation.findAll");
		return repositoryUser.findAll();
	}

	@Override
	public User findByLogin(String login) {
		log.info("Start - UserServiceImplementation.findByLogin - Login - {}", login);

		Optional<User> userOPT = repositoryUser.findByLogin(login);
		if (!userOPT.isPresent()) {
			throw new UserInfoNotFoundException();
		}

		log.info("End - UserServiceImplementation.findByLogin ");
		return userOPT.get();
	}

	@Override
	public List<UserFDTO> buscarContributors(UserHDTO userHDTO) {
		log.info("Start - UserServiceImplementation.buscarContributors - UserHDTO - {}", userHDTO);
		Set<User> userList = new HashSet<>();

		userList.addAll(findAll());

		Optional<RepoInfo> repoInfo = this.repoEndPoint.buscarRepoInfo(userHDTO.getUser(), userHDTO.getRepo());
		if (!repoInfo.isPresent())
			throw new RepoNotFoundException();
		
		boolean buscarUsres = true;
		int page = 1;
		while (buscarUsres) {

			List<User> users = this.repoEndPoint.buscarUsers(userHDTO.getUser(), userHDTO.getRepo(), page);
			if (users.isEmpty())
				buscarUsres = false;
			else
				userList.addAll(users);
			page++;
		}

		saveAll(userList.stream().collect(Collectors.toList()));
		this.exportService.exportarCSV(userList.stream().collect(Collectors.toList()),
				"src/main/resources/csv/user.csv");

		log.info("End - UserServiceImplementation.buscarContributors -  UserFDTO");
		return userList.stream().map(user -> mapper.map(user, UserFDTO.class)).collect(Collectors.toList());
	}

	@Override
	public void saveAll(List<User> userList) {
		log.info("Start - UserServiceImplementation.saveAll");

		repositoryUser.saveAll(userList);

		log.info("End - UserServiceImplementation.saveAll");
	}

}
