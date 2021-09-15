package br.com.uniacademia.cesIC.service;

import br.com.uniacademia.cesIC.dto.LoginDTO;
import br.com.uniacademia.cesIC.dto.token.TokenFRDTO;
import br.com.uniacademia.cesIC.dto.token.TokenRDTO;

public interface SecurityService {

	void lock(Long id);

	void unlock(Long id);

	TokenFRDTO login(LoginDTO loginDTO);

	TokenRDTO refresh(String token);
}
