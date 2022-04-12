package com.searchrank.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.searchrank.constant.ControllerMappings;
import com.searchrank.service.FileService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(ControllerMappings.INGESTDATA)
@Tag(name = "ingets-case-keywords", description = "REST API to ingest and process csv")
public class IngestDataController {

	@Autowired
	private FileService fileService;

	@Operation(summary = "Ingest case-keywords from file")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "successfully ingested"),
			@ApiResponse(responseCode = "400", description = "Invalid file supplied"),
			@ApiResponse(responseCode = "500", description = "Processing file failed")})
	@PostMapping(value = "/ingest-from-file", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<Void> ingestFromFile(@RequestParam("file") MultipartFile file) throws IOException {
		fileService.loadDataFromFile(file);
	    return new ResponseEntity<>(HttpStatus.CREATED);
	  }
	
	@Operation(summary = "Ingest case-keywords from AWS S3")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "successfully ingested"),
			@ApiResponse(responseCode = "400", description = "Invalid details supplied"),
			@ApiResponse(responseCode = "500", description = "Processing file failed")})
	@PostMapping("/ingest-from-s3")
	  public ResponseEntity<Void> ingestFromS3(@RequestParam(value = "bucketName") String bucketName,
				@RequestParam(value = "fileName") String fileName) throws IOException{
		fileService.loadDataFromS3(bucketName, fileName);
	    return new ResponseEntity<>(HttpStatus.CREATED);
	  }

}
