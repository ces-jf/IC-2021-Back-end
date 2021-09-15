package br.com.uniacademia.cesIC.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.uniacademia.cesIC.converter.authentication.AuthenticationFRDTOToJwtUser;
import br.com.uniacademia.cesIC.converter.authentication.AuthenticationToAuthenticationFRDTO;
import br.com.uniacademia.cesIC.converter.authentication.AuthenticationToUserRDTO;
import br.com.uniacademia.cesIC.converter.user.UserPIDTOToAuthentication;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
	ModelMapper modelMapper = new ModelMapper();
	// Authentication
	modelMapper.addConverter(new AuthenticationToUserRDTO());
	modelMapper.addConverter(new AuthenticationFRDTOToJwtUser());
	modelMapper.addConverter(new AuthenticationToAuthenticationFRDTO());

	// User
	modelMapper.addConverter(new UserPIDTOToAuthentication());
	return modelMapper;
    }

}
