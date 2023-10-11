package com.example.rentbike.common.exception;
/**
 * This transaction is thrown when there is not enought information to make a transaction
 */
public class NotEnoughTransactionInfoException extends PaymentException {
	/**
	 * Exception Construction
	 */
public NotEnoughTransactionInfoException() {
	super("ERROR: Not Enough Transaction Information");
}
}
