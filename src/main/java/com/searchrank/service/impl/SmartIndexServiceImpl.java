package com.searchrank.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.searchrank.constant.ErrorMessages;
import com.searchrank.data.CaseRecords;
import com.searchrank.exception.BadRequestException;
import com.searchrank.model.AggregatedRankResponse;
import com.searchrank.model.CaseKeyword;
import com.searchrank.model.IndividualRankResponse;
import com.searchrank.service.SmartIndexService;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

/**
 * {@inheritDoc}
 */
@Slf4j
@Service
public class SmartIndexServiceImpl implements SmartIndexService {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public IndividualRankResponse getIndividualRanks(@NonNull String asin, @NonNull String keyword) {
		List<CaseKeyword> data = CaseRecords.records.stream()
				.filter(t -> (t.getAsin().equals(asin) && t.getKeyword().contains(keyword)))
				.collect(Collectors.toList());
		return new IndividualRankResponse(asin, keyword, data);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public AggregatedRankResponse getAggregatedRanks(String asin, String keyword) {
		List<CaseKeyword> data;
		if (asin != null) {
			log.debug("Loading aggregations records for asin");
			data = CaseRecords.records.stream().filter(t -> (t.getAsin().equals(asin))).collect(Collectors.toList());
			return new AggregatedRankResponse(asin, null, data);
		} else if (keyword != null) {
			log.debug("Loading aggregations records for keyword");
			data = CaseRecords.records.stream().filter(t -> (t.getKeyword().contains(keyword)))
					.collect(Collectors.toList());
			return new AggregatedRankResponse(null, keyword, data);
		} else {
			throw new BadRequestException(ErrorMessages.INVALIDINPUT);
		}
	}

}
