package com.ciphersnippet.exceptionhandler;

public class NoSuchPageFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3173633752400477348L;
	
	public NoSuchPageFoundException(String page) {
        super("No Such " + page.toString() + " found.");
    }

}

//@ResponseStatus(HttpStatus.NOT_FOUND)
//public class NoSuchPageFoundException  extends RuntimeException {
//
//  /**
//	 * 
//	 */
//	private static final long serialVersionUID = 3082482889404058227L;
//
//public NoSuchPageFoundException(String message) {
//
//    super(message);
//
//  }
//
//}

