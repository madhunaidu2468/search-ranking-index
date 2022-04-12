package com.searchrank.constant;

import lombok.experimental.UtilityClass;

/**
 * 
 * This class contains the error message used in the application
 * 
 * @author madhuramaya
 *
 */
@UtilityClass
public class ErrorMessages {

	public static final String NORECORDSFOUND = "No records found";
	
	public static final String INTERNALSERVERERROR = "Internal Server Error";
	
	public static final String FILESIZEEXCEEDED = "File too large";
	
	public static final String FILEPROCESSEXCEPTION = "Processing csv file";
	
	public static final String INVALIDINPUT = "Invalid input. Either asin or keyword mandatory";

}
