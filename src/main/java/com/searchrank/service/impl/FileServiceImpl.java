package com.searchrank.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.opencsv.bean.CsvToBeanBuilder;
import com.searchrank.data.CaseRecords;
import com.searchrank.model.CaseKeyword;
import com.searchrank.service.FileService;

/**
 * {@inheritDoc}
 */
@Service
public class FileServiceImpl implements FileService {

	@Autowired
	AmazonS3 awsS3Client;

	@Autowired
	ResourceLoader resourceLoader;

	public List<CaseKeyword> readInputFile(InputStream inputStream) {
		Reader fileReader = new InputStreamReader(inputStream);
		return new CsvToBeanBuilder<CaseKeyword>(fileReader).withType(CaseKeyword.class).withSeparator(';')
				.withSkipLines(1).withIgnoreEmptyLine(true).build().parse();
	}

	/**
	 * {@inheritDoc}
	 * @throws IOException 
	 * 
	 * @see #readInputFile()
	 */
	@Override
	public void loadDataFromPath(String path) throws IOException {
		Resource resource = resourceLoader.getResource("classpath:" + path);
		CaseRecords.records = this.readInputFile(resource.getInputStream());
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see #readInputFile()
	 */
	@Override
	public void loadDataFromFile(MultipartFile file) throws IOException{
		CaseRecords.records = this.readInputFile(file.getInputStream());
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see #readInputFile()
	 */
	@Override
	public void loadDataFromS3(String bucketName, String keyName) throws IOException{
		S3Object s3Object = awsS3Client
				.getObject(new GetObjectRequest(bucketName, keyName));
		CaseRecords.records = this.readInputFile(s3Object.getObjectContent());
	}
}