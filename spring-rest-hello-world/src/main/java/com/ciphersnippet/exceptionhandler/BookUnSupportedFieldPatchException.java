package com.ciphersnippet.exceptionhandler;

import java.util.Set;

public class BookUnSupportedFieldPatchException extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = -8444853860250378143L;

	public BookUnSupportedFieldPatchException(Set<String> keys) {
        super("Field " + keys.toString() + " update is not allowed.");
    }

}
