package br.com.uniacademia.cesIC.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
@Profile("dev")
public class SwaggerConfig {

	final String EMAIL = "";
	final String LICENSE = "";
	final String WEB_PAGE = "";
	final String VERSION = "0.1";
	final String LICENSE_URL = "";
	final String TERMS_OF_SERVICE_URL = "";
	final String TITLE = "Uniacademia IC2021";
	final String DESCRIPTION = "Serviço - User";
	final Contact CONTRACT = new Contact(TITLE, EMAIL, WEB_PAGE);

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any()).build().apiInfo(apiInfo());
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title(TITLE).description(DESCRIPTION).version(VERSION)
				.termsOfServiceUrl(TERMS_OF_SERVICE_URL).license(LICENSE).licenseUrl(LICENSE_URL).contact(CONTRACT)
				.build();
	}
}
