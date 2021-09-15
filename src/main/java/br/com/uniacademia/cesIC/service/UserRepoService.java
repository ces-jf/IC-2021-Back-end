package br.com.uniacademia.cesIC.service;

import java.util.List;

import br.com.uniacademia.cesIC.dto.userRepo.UserRepoFDTO;
import br.com.uniacademia.cesIC.dto.userRepo.UserRepoHDTO;
import br.com.uniacademia.cesIC.models.UserRepo;

public interface UserRepoService {

	void save(UserRepo user);

	void saveAll(List<UserRepo> userList);

	List<UserRepo> findAll();

	UserRepoFDTO findByLogin(String login);

	List<UserRepoFDTO> buscarContributors(UserRepoHDTO userHDTO);
}
