package br.com.uniacademia.cesIC.service;

import br.com.uniacademia.cesIC.dto.UserInfooDTO;
import br.com.uniacademia.cesIC.models.UserInfoo;

public interface UserInfooService {

	UserInfoo findByLogin(String login);

	void save(UserInfooDTO userInfooDTO);

	UserInfooDTO userInfooToUserInfooDTO(UserInfoo userInfoo);

	UserInfooDTO buscarUserInfoGitHub(String login);
}
