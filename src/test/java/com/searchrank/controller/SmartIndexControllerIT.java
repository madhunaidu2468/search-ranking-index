package com.searchrank.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import com.searchrank.SearchRankIndexApplication;
import com.searchrank.constant.ControllerMappings;
import com.searchrank.data.CaseRecords;
import com.searchrank.model.AggregatedRankResponse;
import com.searchrank.model.CaseKeyword;
import com.searchrank.model.IndividualRankResponse;
import com.searchrank.util.TestUtils;

@ActiveProfiles("test")
@SpringBootTest(classes = SearchRankIndexApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SmartIndexControllerIT {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate testRestTemplate;

	CaseKeyword testData;

	@BeforeEach
	void init() {
		testData = TestUtils.getCaseKeyword();
		CaseRecords.records = Arrays.asList(testData);
	}

	@AfterEach
	void clear() {
		CaseRecords.records = null;
	}

	@Test
	public void getIndividualRanks_success() throws Exception {
		String url = ControllerMappings.SMARTINDEX + ControllerMappings.INDRANKS + "/" + testData.getAsin() + "/"
				+ testData.getKeyword();
		ResponseEntity<IndividualRankResponse> response = testRestTemplate.getForEntity(url,
				IndividualRankResponse.class);
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	
	@Test
	public void getIndividualRanks_nocontents() throws Exception {
		String url = ControllerMappings.SMARTINDEX + ControllerMappings.INDRANKS + "/" + testData.getAsin() + "/"
				+ testData.getKeyword();
		CaseRecords.records = new ArrayList<>();
		ResponseEntity<IndividualRankResponse> response = testRestTemplate.getForEntity(url,
				IndividualRankResponse.class);
		assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
	}
	
	@Test
	public void getAggRanks_success() throws Exception {
		String url = ControllerMappings.SMARTINDEX + ControllerMappings.AGGRANKS + "?asin=" + testData.getAsin() + "&keyword="
				+ testData.getKeyword();
		ResponseEntity<AggregatedRankResponse> response = testRestTemplate.getForEntity(url,
				AggregatedRankResponse.class);
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	
	@Test
	public void getAggRanks_nocontents() throws Exception {
		String url = ControllerMappings.SMARTINDEX + ControllerMappings.AGGRANKS + "?keyword=" + testData.getAsin() + "&asin="
				+ testData.getKeyword();
		CaseRecords.records = new ArrayList<>();
		ResponseEntity<AggregatedRankResponse> response = testRestTemplate.getForEntity(url,
				AggregatedRankResponse.class);
		assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
	}
}
