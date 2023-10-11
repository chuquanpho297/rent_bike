package com.example.rentbike.common.exception;
/**
 * this exception is thrown when there is error but the cause is unknown
 */
public class UnrecognizedException extends RuntimeException {
	/**
	  * Exception Construction
	  */
	public UnrecognizedException() {
		super("ERROR: Something went wrong!");
	}
}
