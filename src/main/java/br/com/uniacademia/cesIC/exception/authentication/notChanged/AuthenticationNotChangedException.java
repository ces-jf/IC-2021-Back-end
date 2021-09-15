package br.com.uniacademia.cesIC.exception.authentication.notChanged;

import br.com.uniacademia.cesIC.constant.ErrorCode;

public class AuthenticationNotChangedException extends RuntimeException {

	private static final long serialVersionUID = -320075757688047046L;

	public AuthenticationNotChangedException() {
		super(ErrorCode.AUTHENTICATION_NOT_CHANGED.getMessage());
	}
}
