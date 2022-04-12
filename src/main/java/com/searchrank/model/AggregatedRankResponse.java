package com.searchrank.model;

import java.util.List;
import java.util.stream.Collectors;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(name="AggregatedRanks Time Series", description = "Time series containing the aggregated ranks")
@NoArgsConstructor
@Data
public class AggregatedRankResponse {

	private String asin;
	
	private String keyword;
	
    @Schema(name = "records", description = "rank and timestamp")
	private List<AggregatedRecord> records;

	
	public AggregatedRankResponse(String asin, String keyword, List<CaseKeyword> data) {
		this.asin = asin;
		this.keyword=keyword;
		this.records=setRecords(data);
	}


	private List<AggregatedRecord> setRecords(List<CaseKeyword> data) {
		 return data.stream().sorted()
				.collect(Collectors.groupingBy(CaseKeyword::getTimestamp))
				.entrySet().stream()
				.map(e -> new AggregatedRecord(e.getKey(), e.getValue().stream().mapToInt(t -> t.getRank()).sum()))
				.collect(Collectors.toList());
	}

}

@Data
@AllArgsConstructor
class AggregatedRecord{

	private Long timestamp;
	
	private Integer rank;

}