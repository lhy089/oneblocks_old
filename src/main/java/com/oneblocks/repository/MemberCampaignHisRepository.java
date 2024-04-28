package com.oneblocks.repository;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.oneblocks.domain.MemberCampaignHis;
import com.oneblocks.parameter.CampaignListSearchParam;

@Repository
public interface MemberCampaignHisRepository {
	
	void insertMemberCampaignOnHistory(MemberCampaignHis memberCampaignHis);

	String getMaxHistoryId(String campaignId);
	
	List<Map<String,String>> getMyCampaignOnPeriod(CampaignListSearchParam campaignListSearchParam);
	
	int setOffMemberCampaignHis(Map<String,Object> data);
}
