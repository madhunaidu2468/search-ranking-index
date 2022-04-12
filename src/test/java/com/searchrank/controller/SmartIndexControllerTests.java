package com.searchrank.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.searchrank.constant.ControllerMappings;
import com.searchrank.exception.BadRequestException;
import com.searchrank.model.AggregatedRankResponse;
import com.searchrank.model.CaseKeyword;
import com.searchrank.model.IndividualRankResponse;
import com.searchrank.service.FileService;
import com.searchrank.service.SmartIndexService;
import com.searchrank.util.TestUtils;


@ExtendWith(SpringExtension.class)
@WebMvcTest
public class SmartIndexControllerTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private SmartIndexService smartIndexService;
	
	@MockBean
	private FileService fileService;
	
	@Test
	public void getIndividualRanks_success() throws Exception {

		IndividualRankResponse expected = TestUtils.getIndividualRankResponse();
		when(smartIndexService.getIndividualRanks(any(String.class), any(String.class))).thenReturn(expected);

		String url = ControllerMappings.SMARTINDEX + ControllerMappings.INDRANKS+ "/{asin}/{keyword}";
		this.mockMvc
		.perform(MockMvcRequestBuilders.get(url, expected.getAsin(), expected.getKeyword())
				.accept(MediaType.APPLICATION_JSON))
		.andDo(print()).andExpect(status().isOk())
		.andExpect(MockMvcResultMatchers.jsonPath("$.asin").value(expected.getAsin()));
	}
	
	@Test
	public void getIndividualRanks_notfound() throws Exception {

		IndividualRankResponse expected = TestUtils.getIndividualRankResponse();
		when(smartIndexService.getIndividualRanks(any(String.class), any(String.class))).thenThrow(RuntimeException.class);

		String url = ControllerMappings.SMARTINDEX + ControllerMappings.INDRANKS+ "/{asin}/{keyword}";
		this.mockMvc
		.perform(MockMvcRequestBuilders.get(url, expected.getAsin(), null)
				.accept(MediaType.APPLICATION_JSON))
		.andDo(print()).andExpect(status().isNotFound());
	}
	
	@Test
	public void getIndividualRanks_internalservererror() throws Exception {

		IndividualRankResponse expected = TestUtils.getIndividualRankResponse();
		when(smartIndexService.getIndividualRanks(any(String.class), any(String.class))).thenThrow(RuntimeException.class);

		String url = ControllerMappings.SMARTINDEX + ControllerMappings.INDRANKS+ "/{asin}/{keyword}";
		this.mockMvc
		.perform(MockMvcRequestBuilders.get(url, expected.getAsin(), expected.getKeyword())
				.accept(MediaType.APPLICATION_JSON))
		.andDo(print()).andExpect(status().isInternalServerError());
	}
	
	@Test
	public void getIndividualRanks_nocontents() throws Exception {

		IndividualRankResponse expected = new IndividualRankResponse("123", "123", new ArrayList<CaseKeyword>());
		when(smartIndexService.getIndividualRanks(any(String.class), any(String.class))).thenReturn(expected);

		String url = ControllerMappings.SMARTINDEX + ControllerMappings.INDRANKS+ "/{asin}/{keyword}";
		this.mockMvc
		.perform(MockMvcRequestBuilders.get(url, expected.getAsin(), expected.getKeyword())
				.accept(MediaType.APPLICATION_JSON))
		.andDo(print()).andExpect(status().isNoContent());
	}
	
	@Test
	public void getAggRanks_success() throws Exception {

		AggregatedRankResponse expected = TestUtils.getAggregatedRankResponse();
		when(smartIndexService.getAggregatedRanks(any(String.class), any(String.class))).thenReturn(expected);

		String url = ControllerMappings.SMARTINDEX + ControllerMappings.AGGRANKS+ "?asin={asin}&keyword={keyword}";
		this.mockMvc
		.perform(MockMvcRequestBuilders.get(url, expected.getAsin(), expected.getKeyword())
				.accept(MediaType.APPLICATION_JSON))
		.andDo(print()).andExpect(status().isOk())
		.andExpect(MockMvcResultMatchers.jsonPath("$.asin").value(expected.getAsin()));
	}
	
	@Test
	public void getAggRanks_badrequest() throws Exception {

		when(smartIndexService.getAggregatedRanks(any(String.class), any(String.class))).thenThrow(BadRequestException.class);

		String url = ControllerMappings.SMARTINDEX + ControllerMappings.AGGRANKS+ "?asin={asin}&keyword={keyword}";
		this.mockMvc
		.perform(MockMvcRequestBuilders.get(url, "1", "1")
				.accept(MediaType.APPLICATION_JSON))
		.andDo(print()).andExpect(status().isBadRequest());
	}
	
	@Test
	public void getAggRanks_nocontents() throws Exception {

		AggregatedRankResponse expected = new AggregatedRankResponse("123", "123", new ArrayList<CaseKeyword>());
		when(smartIndexService.getAggregatedRanks(any(String.class), any(String.class))).thenReturn(expected);

		String url = ControllerMappings.SMARTINDEX + ControllerMappings.AGGRANKS+ "?asin={asin}&keyword={keyword}";
		this.mockMvc
		.perform(MockMvcRequestBuilders.get(url, expected.getAsin(), expected.getKeyword())
				.accept(MediaType.APPLICATION_JSON))
		.andDo(print()).andExpect(status().isNoContent());
	}
}
