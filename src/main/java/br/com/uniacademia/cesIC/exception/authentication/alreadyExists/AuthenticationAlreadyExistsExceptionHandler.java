package br.com.uniacademia.cesIC.exception.authentication.alreadyExists;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.uniacademia.cesIC.util.Response;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class AuthenticationAlreadyExistsExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(AuthenticationAlreadyExistsException.class)
	public final ResponseEntity<Object> exceptionHandler(AuthenticationAlreadyExistsException exception) {
		log.error("AuthenticationAlreadyExistsException - Message: {}", exception);

		Response<Void> response = new Response<>();
		response.addError(exception.getMessage());

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
	}
}
