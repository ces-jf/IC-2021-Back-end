package br.com.uniacademia.cesIC.exception.authentication.alreadyExists;

import br.com.uniacademia.cesIC.constant.ErrorCode;

public class AuthenticationAlreadyExistsException extends RuntimeException {

    private static final long serialVersionUID = -1789831594062558569L;

    public AuthenticationAlreadyExistsException() {
		super(ErrorCode.AUTHENTICATION_ALREADY_EXISTS.getMessage());
	}
}
