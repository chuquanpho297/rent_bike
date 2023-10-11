package com.example.rentbike.common.exception;
/**
 * This exception is thrown when the credit card doesnt 
 * have enough money to process the transaction
 */
public class NotEnoughBalanceException extends PaymentException{

	/**
	 * Exception Construction
	 */
	public NotEnoughBalanceException() {
		super("ERROR: Not enough balance in card!");
	}

}
