package com.searchrank.config;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

/**
 * 
 * This is config params for swagger ui
 * 
 * @author madhuramaya
 *
 */
@OpenAPIDefinition(
		  info = @Info(
		  title = "Search Ranking Index Task",
		  version = "1.0.0",
		  description = "" +
		    "Implementation of task provided",
		  contact = @Contact(
		    name = "Madhu Ramaya", 
		    url = "https://www.linkedin.com/in/madhunaidu2468", 
		    email = "madhunaidu2468@gmail.com"
		  ))
		  )
@Configuration
public class OpenAPIConfig {
}
