package com.searchrank.data;

import java.util.ArrayList;
import java.util.List;

import com.searchrank.model.CaseKeyword;

import lombok.NoArgsConstructor;

/**
 * 
 * This is sample class which holds the records loaded from the csv file in a static variable.
 * This file can be replaced by fetched the data from a database or in memory db
 * 
 * @author madhuramaya
 *
 */
@NoArgsConstructor
public final class CaseRecords {

	/**
	 * This is a static variable which holds the records loaded from csv file. This variable is set on application starup
	 */
	public static List<CaseKeyword> records = new ArrayList<CaseKeyword>();
	
	
}
