package br.com.uniacademia.cesIC.service;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;

import br.com.uniacademia.cesIC.dto.user.UserFDTO;
import br.com.uniacademia.cesIC.dto.user.UserHDTO;
import br.com.uniacademia.cesIC.models.User;

public interface UserService {

	void save(User user);

	void saveAll(List<User> userList);

	List<User> findAll();

	User findByLogin(String login) throws JsonGenerationException, JsonMappingException, IOException;

	List<UserFDTO> buscarContributors(UserHDTO userHDTO);
}
