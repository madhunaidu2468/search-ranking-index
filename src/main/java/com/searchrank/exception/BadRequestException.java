package com.searchrank.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.experimental.StandardException;

/**
 * 
 * This is custom exception class to handle 400 error
 * @author madhuramaya
 *
 */
@StandardException
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeException {

	private static final long serialVersionUID = -2919370868902910288L;

}
