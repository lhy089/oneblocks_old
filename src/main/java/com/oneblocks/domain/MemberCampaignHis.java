package com.oneblocks.domain;

import lombok.Data;

@Data
public class MemberCampaignHis {
	
	private String hisId;
	private String memberId;
	private String campaignId;
	private String onDate;
	private String offDate;
}
