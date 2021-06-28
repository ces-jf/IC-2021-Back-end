package br.com.uniacademia.cesIC.service.implementation;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.uniacademia.cesIC.dto.UserInfooDTO;
import br.com.uniacademia.cesIC.models.UserInfoo;
import br.com.uniacademia.cesIC.repositors.RepositoryUserInfoo;
import br.com.uniacademia.cesIC.service.UserInfooService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserInfooServiceImplementation implements UserInfooService {

	@Autowired
	RepositoryUserInfoo repositoryUserInfoo;

	@Autowired(required = true)
	ModelMapper mapper;

	@Override
	public UserInfoo findByLogin(String login) {
		Optional<UserInfoo> opt = repositoryUserInfoo.findByPorLogin(login);
		if (!opt.isPresent()) {
			log.error("Não tem foi encontrado - Login {}", login);
			return null;
		}
		return opt.get();
	}

	@Override
	public UserInfooDTO buscarUserInfoGitHub(String login) {

		String uri = "https://api.github.com/users/" + login;
		RestTemplate restTemplate = new RestTemplate();
		Map<String, String> params = new HashMap<String, String>();
		params.put("login", login);
		UserInfooDTO userDto = restTemplate.getForObject(uri, UserInfooDTO.class, params);
		if (userDto.equals(null)) {
			log.error("Não tem foi encontrado - Login {}", login);
			return null;
		}
		return userDto;
	}

	@Override
	public void save(UserInfooDTO userInfooDTO) {
		if (!userInfooDTO.equals(null))
			repositoryUserInfoo.save(mapper.map(userInfooDTO, UserInfoo.class));
		else
			log.error("Parametro null - userInfooDTO {}", userInfooDTO);
	}

	@Override
	public UserInfooDTO userInfooToUserInfooDTO(UserInfoo userInfoo) {
		return mapper.map(userInfoo, UserInfooDTO.class);
	}
}
