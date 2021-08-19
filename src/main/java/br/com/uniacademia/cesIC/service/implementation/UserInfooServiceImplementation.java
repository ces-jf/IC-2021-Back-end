package br.com.uniacademia.cesIC.service.implementation;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.uniacademia.cesIC.dto.userInfoo.UserInfooDTO;
import br.com.uniacademia.cesIC.endpoints.UserInfooEndPoint;
import br.com.uniacademia.cesIC.exception.user.notFound.UserInfoNotFoundException;
import br.com.uniacademia.cesIC.models.UserInfoo;
import br.com.uniacademia.cesIC.repositors.RepositoryUserInfoo;
import br.com.uniacademia.cesIC.service.ExportService;
import br.com.uniacademia.cesIC.service.UserInfooService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserInfooServiceImplementation implements UserInfooService {

	@Autowired
	RepositoryUserInfoo repositoryUserInfoo;

	@Autowired
	UserInfooEndPoint userInfooEndPoint;

	@Autowired
	ExportService exportService;

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
	public List<UserInfoo> findAll() {
		return repositoryUserInfoo.findAll();
	}

	@Override
	public UserInfooDTO buscarUserInfoGitHub(String login) {
		log.info("Start - UserInfooServiceImplementation.buscarUserInfoGitHub - Login - {}", login);

		UserInfooDTO userDto = mapper.map(this.userInfooEndPoint.getUserInfoo(login), UserInfooDTO.class);
		if (userDto == null) {
			log.error("Não tem foi encontrado - Login {}", login);
			throw new UserInfoNotFoundException();
		}
		save(userDto);

		log.info("End - UserInfooServiceImplementation.buscarUserInfoGitHub - UserDTO - {}", userDto);
		return userDto;
	}

	@Override
	public void save(UserInfooDTO userInfooDTO) {
		log.info("Start - UserInfooServiceImplementation.save - UserInfooDTO - {}", userInfooDTO);

		if (userInfooDTO != null)
			repositoryUserInfoo.save(mapper.map(userInfooDTO, UserInfoo.class));
		else
			throw new UserInfoNotFoundException();

		log.info("End - UserInfooServiceImplementation.save - UserInfooDTO - {}", userInfooDTO);
	}

	public void exportCSV(List<UserInfoo> params) {
		String path = "src/main/resources/csv/userInfos.csv";
		exportService.exportarCSV(params, path);

	}
}
