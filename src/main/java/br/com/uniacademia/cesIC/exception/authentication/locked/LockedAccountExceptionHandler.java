package br.com.uniacademia.cesIC.exception.authentication.locked;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.uniacademia.cesIC.util.Response;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class LockedAccountExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(LockedAccountException.class)
	public final ResponseEntity<Object> exceptionHandler(LockedAccountException exception) {
		log.error("LockedAccountException - Message: {}", exception);

		Response<Void> response = new Response<>();
		response.addError(exception.getMessage());

		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
	}
}
