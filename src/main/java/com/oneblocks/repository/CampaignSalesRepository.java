package com.oneblocks.repository;

import org.springframework.stereotype.Repository;

import com.oneblocks.domain.CampaignSales;
import com.oneblocks.parameter.CampaignListSearchParam;
import com.oneblocks.vo.NSalesVO;

@Repository
public interface CampaignSalesRepository {
	
	NSalesVO getMyCampaignSalesInfo(CampaignListSearchParam campaignListSearchParam);
	
	int insertCampaignSalesInfo(CampaignSales campaignSalesInfo);

}
