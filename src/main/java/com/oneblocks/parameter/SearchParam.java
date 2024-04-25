package com.oneblocks.parameter;

import lombok.Data;

@Data
public class SearchParam {
	private String dateFlag;
	private String startDate;
	private String endDate;
	private String flag;
	
	private String orderFlag;
	private String orderKind;
	
	private int pageNum;
}
