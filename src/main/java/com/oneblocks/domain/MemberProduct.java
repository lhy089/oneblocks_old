package com.oneblocks.domain;

import lombok.Data;

@Data
public class MemberProduct {
	
	private String memberId;
	private String campaignId;
	private String productId;
	private String onOffYn;
	private String regDt;
	private String updateDt;
}
