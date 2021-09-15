package br.com.uniacademia.cesIC.service;

import br.com.uniacademia.cesIC.dto.LoginDTO;
import br.com.uniacademia.cesIC.dto.user.UserPDTO;

public interface UserService {

	LoginDTO includeCommon(UserPDTO userPDTO);
	
	LoginDTO includeAdministrator(UserPDTO userPDTO);
}
