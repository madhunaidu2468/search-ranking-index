package com.searchrank.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Arrays;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.searchrank.data.CaseRecords;
import com.searchrank.model.AggregatedRankResponse;
import com.searchrank.model.CaseKeyword;
import com.searchrank.model.IndividualRankResponse;
import com.searchrank.service.impl.SmartIndexServiceImpl;
import com.searchrank.util.TestUtils;

@ExtendWith(MockitoExtension.class)
public class SmartIndexServiceTest {

	SmartIndexService smartIndexService;

	CaseKeyword testData;

	@BeforeEach
	void init() {
		smartIndexService = new SmartIndexServiceImpl();
		testData = TestUtils.getCaseKeyword();
		CaseRecords.records = Arrays.asList(testData);
	}

	@AfterEach
	void clear() {
		smartIndexService = null;
		CaseRecords.records = null;
	}

	@Test
	public void getIndividualRanks_success() {
		IndividualRankResponse expected = TestUtils.getIndividualRankResponse(testData.getAsin(),
				testData.getKeyword());
		IndividualRankResponse actual = smartIndexService.getIndividualRanks(expected.getAsin(), expected.getKeyword());
		assertNotNull(actual);
		assertEquals(expected.getAsin(), actual.getAsin());
		assertEquals(expected.getKeyword(), actual.getKeyword());
		assertEquals(expected.getRecords().size(), actual.getRecords().size());
	}

	@Test
	public void getIndividualRanks_throws() {
		NullPointerException exception = Assertions.assertThrows(NullPointerException.class, () -> {
			smartIndexService.getIndividualRanks(null, testData.getKeyword());
		});
		assertEquals("asin is marked non-null but is null", exception.getMessage());
	}

	@Test
	public void getAggregatedRanks_success() {
		AggregatedRankResponse expected = TestUtils.getAggregatedRankResponse(testData.getAsin(),
				testData.getKeyword());
		AggregatedRankResponse actual = smartIndexService.getAggregatedRanks(expected.getAsin(), expected.getKeyword());
		assertNotNull(actual);
		assertEquals(expected.getAsin(), actual.getAsin());
		assertEquals(expected.getKeyword(), actual.getKeyword());
		assertEquals(expected.getRecords().size(), actual.getRecords().size());
	}

}
