package com.ciphersnippet.exceptionhandler;

public class BookNotFoundException extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 2815441196190692308L;

	public BookNotFoundException(Long id) {
        super("Book id not found : " + id);
    }

}
