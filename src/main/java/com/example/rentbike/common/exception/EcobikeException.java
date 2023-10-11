package com.example.rentbike.common.exception;
/**
 * The EcobikeException wraps all unchecked exceptions You can use this
 * exception to inform
 *
 */
public class EcobikeException extends RuntimeException {

	/**
	 * Exception Construction
	 */
    public EcobikeException() {

    }

    /**
	 * Exception Construction
	 * @param message
	 */
    public EcobikeException(String message) {
        super(message);
    }
}