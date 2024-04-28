package com.oneblocks.parameter;

import lombok.Data;

@Data
public class CampaignModifyParam {
	
	private String campaignId;
	private String memberId;
	private String productId;
	private String productName;
	private String productPrice;
	private String onOffYn;
	private String productFlag;

}
