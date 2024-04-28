package com.oneblocks.parameter;

import lombok.Data;

@Data
public class ExcelDownloadParam {
	
	private String pageName;
	private String campaignId;
	private String productId;
	private SearchParam searchParam;

}
