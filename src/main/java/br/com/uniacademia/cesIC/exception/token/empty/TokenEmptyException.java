package br.com.uniacademia.cesIC.exception.token.empty;

import br.com.uniacademia.cesIC.constant.ErrorCode;

public class TokenEmptyException extends RuntimeException {

	private static final long serialVersionUID = -2864456512817685368L;
	
	public TokenEmptyException() {
		super(ErrorCode.TOKEN_EMPTY.getMessage());
	}
}
