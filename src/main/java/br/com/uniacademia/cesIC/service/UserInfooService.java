package br.com.uniacademia.cesIC.service;

import java.util.List;

import br.com.uniacademia.cesIC.dto.userInfoo.UserInfooDTO;
import br.com.uniacademia.cesIC.models.UserInfoo;

public interface UserInfooService {

    void save(UserInfooDTO userInfooDTO);

    List<UserInfoo> findAll();

    UserInfooDTO buscarUserInfoGitHub(String login);
}
