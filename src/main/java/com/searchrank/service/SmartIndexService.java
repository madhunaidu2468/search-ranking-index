package com.searchrank.service;

import com.searchrank.model.AggregatedRankResponse;
import com.searchrank.model.IndividualRankResponse;

import lombok.NonNull;

/**
 * This interface is used for fetching the time series records
 * 
 * @author madhuramaya
 *
 */
public interface SmartIndexService {

	/**
	 * 
	 * This method loads the csv file from path provided and maps it to the CaseKeyword bean
	 * 
	 * @param path The path of the file which has to be loaded. Currently the file is placed in the resources folder.
	 * @return {@link IndividualRankResponse} list of CaseKeyword
	 */
	IndividualRankResponse getIndividualRanks(@NonNull String asin, @NonNull String keyword);
	
	/**
	 * 
	 * This method loads the csv file from path provided and maps it to the CaseKeyword bean
	 * 
	 * @param path The path of the file which has to be loaded. Currently the file is placed in the resources folder.
	 * @return {@link AggregatedRankResponse} list of CaseKeyword
	 */
	AggregatedRankResponse getAggregatedRanks(String asin, String keyword);

}
