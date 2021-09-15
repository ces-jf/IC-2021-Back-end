package br.com.uniacademia.cesIC.service;

import java.util.List;

import br.com.uniacademia.cesIC.constant.AuthenticationRole;
import br.com.uniacademia.cesIC.dto.authentication.AuthenticationFPDTO;
import br.com.uniacademia.cesIC.dto.authentication.AuthenticationFRDTO;
import br.com.uniacademia.cesIC.dto.authentication.AuthenticationRPDTO;
import br.com.uniacademia.cesIC.dto.user.UserPIDTO;

public interface AuthenticationService {

	AuthenticationFRDTO findById(Long id);

	AuthenticationFRDTO findByEmail(String email);

	AuthenticationRPDTO include(UserPIDTO UserPIDTO, List<AuthenticationRole> roles);

	AuthenticationRPDTO edit(AuthenticationFPDTO authentication);
}
