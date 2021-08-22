package br.com.uniacademia.cesIC.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.uniacademia.cesIC.exception.repo.notFound.RepoNotFoundException;

@ControllerAdvice
public class FeignExceptionHandler extends ResponseEntityExceptionHandler{

	@ExceptionHandler(RepoNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFoundException(RepoNotFoundException rnfe,
            HttpServletRequest request) {

		String erro = new RepoNotFoundException().getMessage();
        return new ResponseEntity<>(erro, null, HttpStatus.NOT_FOUND);
    }
}
