package br.com.uniacademia.cesIC.service;

import java.util.List;
import java.util.Set;

import br.com.uniacademia.cesIC.models.User;

public interface UserService {

	void save(User user);

	void saveAll(List<User> userList);

	List<User> findAll();

	User findByLogin(String login);

	Set<User> buscarContributors(String user, String repos);
}
