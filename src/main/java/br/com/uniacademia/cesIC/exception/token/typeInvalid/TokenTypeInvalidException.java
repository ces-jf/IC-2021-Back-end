package br.com.uniacademia.cesIC.exception.token.typeInvalid;

import br.com.uniacademia.cesIC.constant.ErrorCode;

public class TokenTypeInvalidException extends RuntimeException {

	private static final long serialVersionUID = -6129779779617387112L;
	
	public TokenTypeInvalidException() {
		super(ErrorCode.TOKEN_TYPE_INVALID.getMessage());
	}
}
