package br.com.uniacademia.cesIC.exception.userInfo.notFound;

import br.com.uniacademia.cesIC.constant.ErrorCode;

public class UserInfoNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 5818315815113777368L;
	
	public UserInfoNotFoundException() {
		super(ErrorCode.USER_NOT_FOUND.getMessage());
	}

}
