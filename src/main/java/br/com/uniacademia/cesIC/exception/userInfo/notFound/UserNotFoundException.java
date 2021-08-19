package br.com.uniacademia.cesIC.exception.userInfo.notFound;

import br.com.uniacademia.cesIC.constant.ErrorCode;

public class UserNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 5818315815113777368L;
	
	public UserNotFoundException() {
		super(ErrorCode.USER_NOT_FOUND.getMessage());
	}

}
