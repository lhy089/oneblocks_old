package com.oneblocks.domain;

import lombok.Data;

@Data
public class Product {
	private String productId;
	private String campaignId;
	private String productNo;
	private String productName;
	private String productFlag;					// O(option), S(supplement)
	private String productRealName;
	private String groupName;
	private String productPrice;
	
//	private String onOffYn;
}
