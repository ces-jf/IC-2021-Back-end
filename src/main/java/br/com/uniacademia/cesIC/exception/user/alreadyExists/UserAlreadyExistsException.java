package br.com.uniacademia.cesIC.exception.user.alreadyExists;

import br.com.uniacademia.cesIC.constant.ErrorCode;

public class UserAlreadyExistsException extends RuntimeException {

	private static final long serialVersionUID = 5372824953990001045L;

	public UserAlreadyExistsException() {
		super(ErrorCode.USER_ALREADY_EXISTS.getMessage());
	}
}
