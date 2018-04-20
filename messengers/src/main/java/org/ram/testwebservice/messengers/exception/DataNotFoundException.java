package org.ram.testwebservice.messengers.exception;

public class DataNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4572788106042152774L;
	
	public DataNotFoundException(String messages){
		super(messages);
	}
}
