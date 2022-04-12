package com.searchrank.model;

import com.opencsv.bean.CsvBindByPosition;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CaseKeyword implements Comparable<CaseKeyword> {

	@CsvBindByPosition(position = 0)
	private Long timestamp;

	@CsvBindByPosition(position = 1)
	private String asin;

	@CsvBindByPosition(position = 2)
	private String keyword;

	@CsvBindByPosition(position = 3)
	private Integer rank;

	
	@Override
	public int compareTo(CaseKeyword o) {
		if (o.getTimestamp() > this.getTimestamp()) {
			return 1;
		} else if (o.getTimestamp() < this.getTimestamp()) {
			return -1;
		} else {
			return 0;
		}
	}


	public void setTimestamp(String timestamp) {
		this.timestamp = Long.parseLong(timestamp);
	}


	public void setRank(String rank) {
		this.rank = Integer.parseInt(rank);
	}

}