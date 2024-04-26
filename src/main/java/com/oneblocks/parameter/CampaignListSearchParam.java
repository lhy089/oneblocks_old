package com.oneblocks.parameter;

import java.util.List;
import java.util.Map;

import lombok.Data;

@Data
public class CampaignListSearchParam {
	private String memberId;
	private String campaignId;
	private String productId;
	private SearchParam searchParam;
	private List<Map<String,String>> dateList;
}
