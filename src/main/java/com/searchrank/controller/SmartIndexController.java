package com.searchrank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.searchrank.constant.ControllerMappings;
import com.searchrank.model.AggregatedRankResponse;
import com.searchrank.model.IndividualRankResponse;
import com.searchrank.service.SmartIndexService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(ControllerMappings.SMARTINDEX)
@Tag(name = "smart-ranking-index", description = "Smart ranking index REST API")
public class SmartIndexController {

	@Autowired
	private SmartIndexService smartIndexService;

	@Operation(summary = "Get individual ranks for an ASIN, for a certain keyword")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "time series response", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = IndividualRankResponse.class)) }),
			@ApiResponse(responseCode = "204", description = "No records found", content = @Content) })
	@GetMapping(value = ControllerMappings.INDRANKS+"/{asin}/{keyword}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<IndividualRankResponse> getIndividualRanks(
			@Parameter(description = "asin to be searched", required = true) @PathVariable("asin") String asin,
			@Parameter(description = "keyword to be searched", required = true) @PathVariable("keyword") String keyword) {
		IndividualRankResponse response = smartIndexService.getIndividualRanks(asin, keyword);
		if (response.getRecords().isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@Operation(summary = "Get aggregated ranks for all ASINs for a certain keyword/all keywords for a certain ASIN")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "time series response", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = AggregatedRankResponse.class)) }),
			@ApiResponse(responseCode = "204", description = "No records found", content = @Content) })
	@GetMapping(value = ControllerMappings.AGGRANKS, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AggregatedRankResponse> getAggregatedRanksOfKeyword(
			@RequestParam(value = "keyword", required = false) String keyword,
			@RequestParam(value = "asin", required = false) String asin) {
		AggregatedRankResponse response = smartIndexService.getAggregatedRanks(asin, keyword);
		if (response.getRecords().isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
