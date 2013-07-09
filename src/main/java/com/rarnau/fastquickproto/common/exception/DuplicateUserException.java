package com.rarnau.fastquickproto.common.exception;

import com.rarnau.fastquickproto.common.Messages;

public class DuplicateUserException extends FrasquitoException {

	private static final long serialVersionUID = 1L;

	@Override
	public String getMessageCode() {
		return Messages.CODE_DUPLICATE_USER;
	}

	
}
