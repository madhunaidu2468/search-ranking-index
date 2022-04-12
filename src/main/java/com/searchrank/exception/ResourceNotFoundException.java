package com.searchrank.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.searchrank.constant.ErrorMessages;

import lombok.experimental.StandardException;

/**
 * 
 * This is custom exception class to handle 404 error
 * @author madhuramaya
 *
 */
@StandardException
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -2919370868902910288L;
	
	public ResourceNotFoundException() {
        super(ErrorMessages.NORECORDSFOUND);
    }

}
