package br.com.uniacademia.cesIC.exception.authentication.notFound;

import br.com.uniacademia.cesIC.constant.ErrorCode;

public class AuthenticationNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -2226994065380175735L;

	public AuthenticationNotFoundException() {
		super(ErrorCode.AUTHENTICATION_NOT_FOUND.getMessage());
	}
}
