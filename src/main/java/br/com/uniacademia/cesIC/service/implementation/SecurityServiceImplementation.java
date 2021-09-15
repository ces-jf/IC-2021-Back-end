package br.com.uniacademia.cesIC.service.implementation;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.uniacademia.cesIC.dto.LoginDTO;
import br.com.uniacademia.cesIC.dto.token.TokenFRDTO;
import br.com.uniacademia.cesIC.dto.token.TokenRDTO;
import br.com.uniacademia.cesIC.dto.user.UserTokenHRDTO;
import br.com.uniacademia.cesIC.models.Authentication;
import br.com.uniacademia.cesIC.repositors.AuthenticationRepository;
import br.com.uniacademia.cesIC.service.SecurityService;
import br.com.uniacademia.cesIC.service.processor.AuthenticationProcessor;
import br.com.uniacademia.cesIC.service.processor.SecurityProcessor;
import br.com.uniacademia.cesIC.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SecurityServiceImplementation implements SecurityService {

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private JwtUtil jwtTokenUtil;

	@Autowired
	private SecurityProcessor securityProcessor;

	@Autowired
	private AuthenticationProcessor authenticationProcessor;

	@Autowired
	private AuthenticationRepository authenticationRepository;

	@Override
	public TokenFRDTO login(LoginDTO loginDTO) {
		log.info("Start - SecurityServiceImplementation.login - LoginDTO: {}", loginDTO);

		Authentication authentication = this.securityProcessor.check(loginDTO.getEmail());
		this.securityProcessor.matchPassword(loginDTO.getPassword(), authentication.getPassword());

		String token = this.securityProcessor.authenticate(loginDTO.getEmail(), loginDTO.getPassword());

		UserTokenHRDTO userTokenHRDTO = this.mapper.map(authentication.getUser(), UserTokenHRDTO.class);

		TokenFRDTO tokenFRDTO = TokenFRDTO.builder().token(token).user(userTokenHRDTO).roles(authentication.getRole())
				.build();

		log.info("End - SecurityServiceImplementation.login - TokenFRDTO: {}", tokenFRDTO);
		return tokenFRDTO;
	}

	@Override
	public TokenRDTO refresh(String token) {
		log.info("Start - SecurityServiceImplementation.refresh - Token: {}", token);

		token = token.substring(7);
		String refreshedToken = this.jwtTokenUtil.refresh(token);

		TokenRDTO returnedToken = new TokenRDTO(refreshedToken);

		log.info("End - SecurityServiceImplementation.refresh - TokenRDTO: {}", returnedToken);
		return returnedToken;
	}

	@Override
	public void lock(Long id) {
		log.info("Start - SecurityServiceImplementation.lock - Id: {}", id);

		Authentication authentication = this.authenticationProcessor.exists(id);
		authentication.setIsLocked(true);

		this.authenticationRepository.save(authentication);

		log.info("End - SecurityServiceImplementation.lock");
	}

	@Override
	public void unlock(Long id) {
		log.info("Start - SecurityServiceImplementation.unlock - Id: {}", id);

		Authentication authentication = this.authenticationProcessor.exists(id);
		authentication.setIsLocked(false);

		this.authenticationRepository.save(authentication);

		log.info("End - SecurityServiceImplementation.unlock");
	}
}
