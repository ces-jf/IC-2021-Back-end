package br.com.uniacademia.cesIC.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.uniacademia.cesIC.exception.repo.notFound.RepoNotFoundException;
import br.com.uniacademia.cesIC.exception.user.notFound.UserNotFoundException;
import br.com.uniacademia.cesIC.exception.userInfo.notFound.UserInfoNotFoundException;

@ControllerAdvice
public class FeignExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(RepoNotFoundException.class)
	public ResponseEntity<?> handleResourceNotFoundException(RepoNotFoundException rnfe, HttpServletRequest request) {

		String erro = new RepoNotFoundException().getMessage();
		return new ResponseEntity<>(erro, null, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(UserInfoNotFoundException.class)
	public ResponseEntity<?> handleResourceNotFoundException(UserInfoNotFoundException rnfe,
			HttpServletRequest request) {

		String erro = new UserInfoNotFoundException().getMessage();
		return new ResponseEntity<>(erro, null, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<?> handleResourceNotFoundException(UserNotFoundException rnfe, HttpServletRequest request) {

		String erro = new UserNotFoundException().getMessage();
		return new ResponseEntity<>(erro, null, HttpStatus.NOT_FOUND);
	}
}
