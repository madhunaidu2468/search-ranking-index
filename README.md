# Search Ranking Index

This is an implementation for an interview assignment provided by [Sellics SaaS products](https://sellics.com/)

## Description:
- The application loads the csv file on startup and stores it in a static variable. This can be replaced by an inmemory db or any other db in future
- To add different csv file, use the api's '/ingest-data/ingest-from-file' or '/ingest-from-s3'. New csv file will override the existing csv data 
- Use '/smart-index/ind-ranks/{asin}/{keyword}' api to get time series containing the individual ranks for an ASIN, for a certain keyword
- Use '/smart-index/agg-ranks?keyword={keyword}' api to get time series containing the aggregated ranks for all ASINs for a certain keyword
- Use '/smart-index/agg-ranks?asin={asin}' api to get time series containing the aggregated ranks of all keywords for a certain ASIN

## Content

* [Main used technologies](#main-technologies-used)
* [Prerequisites](#prerequisites)
* [Build and Run](#build-and-run)
* [Project structure](#project-structure)
* [Libraries Used](#libraries-used)

### 🔨 Main technologies used

- [Java 11](https://developer.oracle.com/java11/), the world’s most popular development platform
- [Spring Boot](https://spring.io/projects/spring-boot), an open source Java-based framework
- [Maven](https://maven.apache.org/what-is-maven.html), a software project management and comprehension tool

### Prerequisites

You will need to have Java 11 installed. Refer [here](https://developer.oracle.com/java11/) for installation instructions

### Build and Run

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

1. Clone the code from git, go to the project folder and install dependencies:

```sh
mvn clean install
```

2. Start the application

```sh
mvn spring-boot:run
```

3. To access the application [http://localhost:8080/api/v1/swagger-ui/index.html](http://localhost:8080/api/v1/swagger-ui/index.html) in your browser

![app](images/app.PNG)

4. To run test cases

```sh
mvn test
```

## Project structure

```
src/                                    project source code
|- main/                                main code
|  |- java/                             core java module
|    |- com.searchrank/                 package
|      |- SearchRankIndexApplication    main file to start the appliation
|      |- RunAfterStartup               file which ingests csv data on application startup
|      |- config/                       consists of config file sto set AWS configuration
|      |- constant                      consists of constants file for commonly used strings
|      |- controller                    rest controller implemented using spring boot
|      |- data                          consists of file which holds the processed csv file data
|      |- exception                     custom exception classes
|      |- model                         dto model classes
|      |- service                       classes implementing business logic to return the data requested by user
|  |- resources/                        contains application yaml file for server config and downloaded csv file from s3
|- test/                                test cases implementation
```

### Libraries Used

- [Lombok](https://projectlombok.org/)
- [Opencsv](http://opencsv.sourceforge.net/)




