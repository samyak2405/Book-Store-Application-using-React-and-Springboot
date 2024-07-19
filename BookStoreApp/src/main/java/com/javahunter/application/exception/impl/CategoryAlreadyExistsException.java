package com.javahunter.application.exception.impl;

public class CategoryAlreadyExistsException extends RuntimeException {
    public CategoryAlreadyExistsException(){
        super();
    }
    public CategoryAlreadyExistsException(String message){
        super(message);
    }

    public CategoryAlreadyExistsException(String message,Throwable cause){
        super(message, cause);
    }

    public CategoryAlreadyExistsException(Throwable cause){
        super(cause);
    }
}
