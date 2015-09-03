package com.webgocommerce.shared;

import java.io.Serializable;

public class UnknownException extends Exception implements Serializable {
	private static final long serialVersionUID = -3716210112460968735L;
	public UnknownException(){
    }
    public UnknownException(String message) {
        super(message);
    }
}