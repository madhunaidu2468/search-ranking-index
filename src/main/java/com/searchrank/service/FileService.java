package com.searchrank.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

/**
 * This interface is used for reading the processing input files
 * 
 * @author madhuramaya
 *
 */
public interface FileService {

	/**
	 * 
	 * This method loads the csv file from the resources folder and maps it to the
	 * CaseKeyword bean
	 * 
	 * @param file The file present in resources folder
	 */
	public void loadDataFromPath(String path) throws IOException;

	/**
	 * 
	 * This method loads the csv file uploaded and maps it to the CaseKeyword bean
	 * 
	 * @param file The file which has to be loaded
	 */
	void loadDataFromFile(MultipartFile file) throws IOException;

	/**
	 *
	 * This method downloads the csv file from aws s3 and laods data
	 * 
	 * @param url The url of the aws s3 file which has to be loaded.
	 */
	void loadDataFromS3(String bucketName, String fileName) throws IOException;

}
