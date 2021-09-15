package br.com.uniacademia.cesIC.converter.authentication;

import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;

import br.com.uniacademia.cesIC.dto.authentication.AuthenticationFRDTO;
import br.com.uniacademia.cesIC.dto.user.UserHRDTO;
import br.com.uniacademia.cesIC.models.Authentication;

public class AuthenticationToAuthenticationFRDTO implements Converter<Authentication, AuthenticationFRDTO> {

	@Override
	public AuthenticationFRDTO convert(MappingContext<Authentication, AuthenticationFRDTO> context) {
		Authentication source = context.getSource();

		UserHRDTO user = UserHRDTO.builder().name(source.getUser().getName()).build();

		return AuthenticationFRDTO.builder().id(source.getId()).email(source.getEmail()).password(source.getPassword())
				.isLocked(source.getIsLocked()).role(source.getRole()).user(user).build();
	}
}
