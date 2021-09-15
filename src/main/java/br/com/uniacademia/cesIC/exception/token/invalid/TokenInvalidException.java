package br.com.uniacademia.cesIC.exception.token.invalid;

import br.com.uniacademia.cesIC.constant.ErrorCode;

public class TokenInvalidException extends RuntimeException {

	private static final long serialVersionUID = -2864456512817685368L;

	public TokenInvalidException() {
		super(ErrorCode.TOKEN_INVALID.getMessage());
	}
}
