package com.oneblocks.repository;

import org.springframework.stereotype.Repository;

import com.oneblocks.domain.Campaign;
import com.oneblocks.parameter.CampaignListSearchParam;

@Repository
public interface CampaignRepository {
	
	Campaign getCampaignByCampaignId(CampaignListSearchParam campaignListSearchParam);
	
	Campaign getCampaignInfo(Campaign campaign);
	
	void insertCampaignInfo(Campaign campaign);

}
