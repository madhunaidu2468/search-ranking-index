package com.searchrank;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.searchrank.constant.ErrorMessages;
import com.searchrank.service.FileService;

import lombok.extern.slf4j.Slf4j;

/**
 * This class is triggered on start of app to ingest and process data from csv file present in resources folder
 * 
 * @author madhuramaya
 *
 */
@Slf4j
@Component
@Profile("!test")
public class RunAfterStartup {

	@Autowired
	private FileService fileService;

	@Async
	@EventListener(ApplicationReadyEvent.class)
	public void runAfterStartup() {
		log.info("Ingest and process file downloaded from aws s3");
		this.processCaseKeywordsFile();
	}

	private void processCaseKeywordsFile() {
		try {
			fileService.loadDataFromPath("case-keywords.csv");
		} catch (IOException e) {
			log.error(ErrorMessages.FILEPROCESSEXCEPTION);
		}
	}

}
