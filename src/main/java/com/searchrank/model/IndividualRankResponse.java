package com.searchrank.model;

import java.util.List;
import java.util.stream.Collectors;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(name="IndividualRanks Time Series", description = "Time series containing the individual ranks")
@Data
@NoArgsConstructor
public class IndividualRankResponse {

	private String asin;
	
	private String keyword;
	
    @Schema(name = "records", description = "rank and timestamp")
	private List<IndividualRecord> records;

	
	public IndividualRankResponse(String asin, String keyword, List<CaseKeyword> data) {
		this.asin = asin;
		this.keyword=keyword;
		this.records=setRecords(data);
	}

	private List<IndividualRecord> setRecords(List<CaseKeyword> data) {
		return data.stream().sorted().map(t -> new IndividualRecord(t)).collect(Collectors.toList());
	}

}

@Data
class IndividualRecord{

	private Long timestamp;
	
	private Integer rank;
	
	public IndividualRecord(CaseKeyword caseKeyword) {
		this.timestamp = caseKeyword.getTimestamp();
		this.rank = caseKeyword.getRank();
	}
}