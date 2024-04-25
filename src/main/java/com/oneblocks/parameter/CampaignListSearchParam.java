package com.oneblocks.parameter;

import lombok.Data;

@Data
public class CampaignListSearchParam {
	private String memberId;
	private String campaignId;
	private SearchParam searchParam;
}
