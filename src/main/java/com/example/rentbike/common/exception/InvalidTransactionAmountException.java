package com.example.rentbike.common.exception;
/**
 * The EcobikeException wraps all unchecked exceptions You can use this
 * exception to inform
 */
public class InvalidTransactionAmountException extends PaymentException {
	public InvalidTransactionAmountException() {
		super("ERROR: Invalid Transaction Amount!");
	}
}
