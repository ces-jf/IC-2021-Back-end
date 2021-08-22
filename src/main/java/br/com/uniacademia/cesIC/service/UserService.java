package br.com.uniacademia.cesIC.service;

import java.util.List;

import br.com.uniacademia.cesIC.dto.user.UserFDTO;
import br.com.uniacademia.cesIC.dto.user.UserHDTO;
import br.com.uniacademia.cesIC.models.User;

public interface UserService {

	void save(User user);

	void saveAll(List<User> userList);

	List<User> findAll();

	UserFDTO findByLogin(String login);

	List<UserFDTO> buscarContributors(UserHDTO userHDTO);
}
