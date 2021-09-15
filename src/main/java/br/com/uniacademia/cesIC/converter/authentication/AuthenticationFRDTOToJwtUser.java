package br.com.uniacademia.cesIC.converter.authentication;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import br.com.uniacademia.cesIC.dto.authentication.AuthenticationFRDTO;
import br.com.uniacademia.cesIC.security.entity.JwtUser;

public class AuthenticationFRDTOToJwtUser implements Converter<AuthenticationFRDTO, JwtUser> {

	@Override
	public JwtUser convert(MappingContext<AuthenticationFRDTO, JwtUser> context) {
		AuthenticationFRDTO source = context.getSource();

		List<GrantedAuthority> authorities = source.getRole().stream()
				.map(role -> new SimpleGrantedAuthority(role.name())).collect(Collectors.toList());

		return JwtUser.builder().id(source.getId()).email(source.getEmail()).password(source.getPassword())
				.authorities(authorities).build();
	}
}
