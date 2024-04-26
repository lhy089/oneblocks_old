package com.oneblocks.repository;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.oneblocks.parameter.CampaignListSearchParam;
import com.oneblocks.vo.NSalesVO;

@Repository
public interface MemberCampaignRepository {
	
	List<NSalesVO> getList(CampaignListSearchParam campaignListSearchParam);
	
	List<Map<String,String>> getMyCampaignOnPeriod(CampaignListSearchParam campaignListSearchParam);

}
