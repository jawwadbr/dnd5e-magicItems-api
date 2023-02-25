package com.jawbr.exceptionHandler;

public class MagicItemNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public MagicItemNotFoundException() {
	}

	public MagicItemNotFoundException(String message) {
		super(message);
	}

	public MagicItemNotFoundException(Throwable cause) {
		super(cause);
	}

	public MagicItemNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public MagicItemNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
