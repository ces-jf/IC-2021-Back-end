package br.com.uniacademia.cesIC.converter.user;

import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;

import br.com.uniacademia.cesIC.dto.user.UserPIDTO;
import br.com.uniacademia.cesIC.models.Authentication;
import br.com.uniacademia.cesIC.models.User;

public class UserPIDTOToAuthentication implements Converter<UserPIDTO, Authentication> {

	@Override
	public Authentication convert(MappingContext<UserPIDTO, Authentication> context) {
		UserPIDTO source = context.getSource();

		User user = User.builder().name(source.getName()).build();

		return Authentication.builder().email(source.getAuthentication().getEmail())
				.password(source.getAuthentication().getPassword()).user(user).build();
	}
}
