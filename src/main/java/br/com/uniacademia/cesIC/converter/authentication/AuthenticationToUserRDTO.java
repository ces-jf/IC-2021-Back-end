package br.com.uniacademia.cesIC.converter.authentication;

import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;

import br.com.uniacademia.cesIC.dto.authentication.AuthenticationRPDTO;
import br.com.uniacademia.cesIC.dto.user.UserRDTO;
import br.com.uniacademia.cesIC.models.Authentication;

public class AuthenticationToUserRDTO implements Converter<Authentication, UserRDTO> {

	@Override
	public UserRDTO convert(MappingContext<Authentication, UserRDTO> context) {
		Authentication source = context.getSource();

		AuthenticationRPDTO authentication = AuthenticationRPDTO.builder().email(source.getEmail())
				.password(source.getPassword()).build();

		return UserRDTO.builder().name(source.getUser().getName()).authentication(authentication).build();
	}
}
