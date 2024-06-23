package com.oneblocks.domain;

import lombok.Data;

@Data
public class CampaignSales {

	private String salesId;
	private String campaignId;
	private int campaignPrice;
	private int salesQuantity;
	private int revenue;
	private int stockQuantity;
	private String updateDate;
}
