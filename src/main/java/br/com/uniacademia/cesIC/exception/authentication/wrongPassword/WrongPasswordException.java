package br.com.uniacademia.cesIC.exception.authentication.wrongPassword;

import br.com.uniacademia.cesIC.constant.ErrorCode;

public class WrongPasswordException extends RuntimeException {

	private static final long serialVersionUID = -3904671552829840705L;

	public WrongPasswordException() {
		super(ErrorCode.WRONG_PASSWORD.getMessage());
	}
}
