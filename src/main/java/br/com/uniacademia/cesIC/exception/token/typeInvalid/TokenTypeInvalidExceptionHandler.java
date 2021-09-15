package br.com.uniacademia.cesIC.exception.token.typeInvalid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.uniacademia.cesIC.util.Response;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class TokenTypeInvalidExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(TokenTypeInvalidException.class)
	public final ResponseEntity<Object> exceptionHandler(TokenTypeInvalidException exception) {
		log.error("TokenTypeInvalidExceptionHandler - Message: {}", exception.getMessage());

		Response<Void> response = new Response<>();
		response.addError(exception.getMessage());

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
	}
}
