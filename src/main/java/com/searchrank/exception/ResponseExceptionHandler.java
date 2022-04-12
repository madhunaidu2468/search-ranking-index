package com.searchrank.exception;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.searchrank.constant.ErrorMessages;

/**
 * This is a global exception handler which handles exceptions thrown across all
 * controllers
 * 
 * @author madhuramaya
 *
 */
@ControllerAdvice
public class ResponseExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	protected ResponseEntity<Object> handleNotFound(RuntimeException ex, WebRequest request) {
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(BadRequestException.class)
	protected ResponseEntity<Object> handleBadrequest(RuntimeException ex, WebRequest request) {
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(RuntimeException.class)
	protected ResponseEntity<Object> handleAllOtherException(RuntimeException ex, WebRequest request) {
		return new ResponseEntity<>(ErrorMessages.INTERNALSERVERERROR, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(MaxUploadSizeExceededException.class)
	public ResponseEntity<Object> handleMaxSizeException(MaxUploadSizeExceededException exc) {
		return new ResponseEntity<>(ErrorMessages.FILESIZEEXCEEDED, HttpStatus.EXPECTATION_FAILED);
	}
	
	@ExceptionHandler(IOException.class)
	protected ResponseEntity<Object> handleIOException(RuntimeException ex, WebRequest request) {
		return new ResponseEntity<>(ErrorMessages.FILEPROCESSEXCEPTION, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
