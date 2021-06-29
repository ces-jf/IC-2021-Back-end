package br.com.uniacademia.cesIC.service.implementation;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.uniacademia.cesIC.config.RestTemplateConfig;
import br.com.uniacademia.cesIC.models.User;
import br.com.uniacademia.cesIC.repositors.RepositoryUser;
import br.com.uniacademia.cesIC.service.UserService;

@Service
public class UserServiceImplementation implements UserService {

	@Autowired
	RepositoryUser repositoryUser;

	@Autowired
	RestTemplateConfig restTemplate;

	@Override
	public void save(User user) {
		if (user.equals(null))
			repositoryUser.save(user);

	}

	@Override
	public List<User> findAll() {
		return repositoryUser.findAll();
	}

	@Override
	public User findByLogin(String login) {
		Optional<User> userOPT = repositoryUser.findByLogin(login);
		if (userOPT.isPresent())
			return userOPT.get();
		return null;
	}

	@Override
	public Set<User> buscarContributors(String user, String repos) {
		Set<User> userList = new HashSet<User>();

		userList.addAll(findAll());

		boolean buscarUsres = true;
		int page = 1;
		while (buscarUsres) {
			String uri2 = "https://api.github.com/repos/" + user + "/" + repos + "/contributors?per_page=100&page="
					+ page;
			ResponseEntity<List<User>> users = restTemplate.restTemplate().exchange(uri2, HttpMethod.GET, null,
					new ParameterizedTypeReference<List<User>>() {
					});
			if (users.getBody().isEmpty())
				buscarUsres = false;
			else
				userList.addAll(users.getBody());
			page++;
		}
		List<User> urs = new ArrayList<User>();
		urs.addAll(userList);
		saveAll(urs);
		return userList;
	}

	@Override
	public void saveAll(List<User> userList) {
		repositoryUser.saveAll(userList);
	}

}
