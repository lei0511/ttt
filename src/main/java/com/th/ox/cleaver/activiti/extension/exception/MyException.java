package com.th.ox.cleaver.activiti.extension.exception;

public class MyException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private String message;
	
	  public MyException(String message){
	    super(message);
	    this.message=message;
	  }
	
	  @Override
	  public String getMessage() {
	    return message;
	  }
	
	  public void setMessage(String message) {
	    this.message = message;
	  }
}
