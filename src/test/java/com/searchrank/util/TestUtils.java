package com.searchrank.util;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.apache.commons.lang3.RandomStringUtils;

import com.searchrank.model.AggregatedRankResponse;
import com.searchrank.model.CaseKeyword;
import com.searchrank.model.IndividualRankResponse;

import lombok.experimental.UtilityClass;

@UtilityClass
public class TestUtils {
	
	public static IndividualRankResponse getIndividualRankResponse() {
		String asin = RandomStringUtils.randomAlphabetic(4);
		String keyword = RandomStringUtils.randomAlphabetic(4);
		List<CaseKeyword> data = Arrays.asList(getCaseKeyword(asin, keyword));
		return new IndividualRankResponse(asin, keyword, data);
	}
	
	public static IndividualRankResponse getIndividualRankResponse(String asin, String keyword) {
		List<CaseKeyword> data = Arrays.asList(getCaseKeyword(asin, keyword));
		return new IndividualRankResponse(asin, keyword, data);
	}
	
	public static AggregatedRankResponse getAggregatedRankResponse() {
		String asin = RandomStringUtils.randomAlphabetic(4);
		String keyword = RandomStringUtils.randomAlphabetic(4);
		List<CaseKeyword> data = Arrays.asList(getCaseKeyword(asin, keyword));
		return new AggregatedRankResponse(asin, keyword, data);
	}
	
	public static AggregatedRankResponse getAggregatedRankResponse(String asin, String keyword) {
		List<CaseKeyword> data = Arrays.asList(getCaseKeyword(asin, keyword));
		return new AggregatedRankResponse(asin, keyword, data);
	}
	
	public static CaseKeyword getCaseKeyword() {
		String asin = RandomStringUtils.randomAlphabetic(4);
		String keyword = RandomStringUtils.randomAlphabetic(4);
		long timeStamp = new Random().nextLong();
		int rank = new Random().nextInt();
		return new CaseKeyword(timeStamp, asin, keyword, rank);
	}
	
	public static CaseKeyword getCaseKeyword(String asin, String keyword) {
		long timeStamp = new Random().nextLong();
		int rank = new Random().nextInt();
		return new CaseKeyword(timeStamp, asin, keyword, rank);
	}

}
