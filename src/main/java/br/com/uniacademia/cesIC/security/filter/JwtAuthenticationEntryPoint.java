package br.com.uniacademia.cesIC.security.filter;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.uniacademia.cesIC.constant.ErrorCode;
import br.com.uniacademia.cesIC.util.Response;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException {
		Response<Void> responseObject = new Response<>();
		responseObject.addError(ErrorCode.ACCESS_DENIED.getMessage());

		response.resetBuffer();
		response.setStatus(HttpStatus.UNAUTHORIZED.value());
		response.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");
		response.getOutputStream().print(new ObjectMapper().writeValueAsString(responseObject));
		response.flushBuffer();
	}
}
