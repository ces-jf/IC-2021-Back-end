package br.com.uniacademia.cesIC.service.implementation;

import java.util.Arrays;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.uniacademia.cesIC.constant.AuthenticationRole;
import br.com.uniacademia.cesIC.dto.LoginDTO;
import br.com.uniacademia.cesIC.dto.authentication.AuthenticationRPDTO;
import br.com.uniacademia.cesIC.dto.user.UserPDTO;
import br.com.uniacademia.cesIC.dto.user.UserPIDTO;
import br.com.uniacademia.cesIC.service.AuthenticationService;
import br.com.uniacademia.cesIC.service.UserService;
import br.com.uniacademia.cesIC.service.processor.UserProcessor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserServiceImplementation implements UserService {

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private UserProcessor userProcessor;

	@Autowired
	private AuthenticationService authenticationService;

	@Override
	public LoginDTO includeCommon(UserPDTO userPDTO) {
		log.info("Start - UserServiceImplementation.include - UserPDTO: {}", userPDTO);

		this.userProcessor.alreadyExists(userPDTO.getName());

		UserPIDTO userPIDTO = this.mapper.map(userPDTO, UserPIDTO.class);

		List<AuthenticationRole> roles = Arrays.asList(AuthenticationRole.COMMON);

		AuthenticationRPDTO authenticationRDTO = this.authenticationService.include(userPIDTO, roles);
		LoginDTO loginDTO = this.mapper.map(authenticationRDTO, LoginDTO.class);

		log.info("End - UserServiceImplementation.include - LoginDTO: {}", loginDTO);
		return loginDTO;
	}

	@Override
	public LoginDTO includeAdministrator(UserPDTO userPDTO) {
		log.info("Start - UserServiceImplementation.include - UserPDTO: {}", userPDTO);

		this.userProcessor.alreadyExists(userPDTO.getName());

		UserPIDTO userPIDTO = this.mapper.map(userPDTO, UserPIDTO.class);

		List<AuthenticationRole> roles = Arrays.asList(AuthenticationRole.ADMINISTRATOR);

		AuthenticationRPDTO authenticationRDTO = this.authenticationService.include(userPIDTO, roles);
		LoginDTO loginDTO = this.mapper.map(authenticationRDTO, LoginDTO.class);

		log.info("End - UserServiceImplementation.include - LoginDTO: {}", loginDTO);
		return loginDTO;
	}
}
