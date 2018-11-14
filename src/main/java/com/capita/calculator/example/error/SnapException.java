package com.capita.calculator.example.error;

public class SnapException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String exceptionMsg; 
	public SnapException(String exceptionMsg) 
	    { 
		 this.exceptionMsg = exceptionMsg;
	    }
}
