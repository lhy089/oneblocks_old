package com.oneblocks.repository;

import org.springframework.stereotype.Repository;

import com.oneblocks.parameter.CampaignListSearchParam;
import com.oneblocks.vo.NSalesVO;

@Repository
public interface CampaignSalesRepository {
	
	NSalesVO getMyCampaignSalesInfo(CampaignListSearchParam campaignListSearchParam);

}
