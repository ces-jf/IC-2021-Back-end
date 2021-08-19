package br.com.uniacademia.cesIC.exception.repo.notFound;

import br.com.uniacademia.cesIC.constant.ErrorCode;

public class RepoNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 5818315815113777368L;
	
	public RepoNotFoundException() {
		super(ErrorCode.REPO_NOT_FOUND.getMessage());
	}

}
