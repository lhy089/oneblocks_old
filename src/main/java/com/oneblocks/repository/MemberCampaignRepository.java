package com.oneblocks.repository;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.oneblocks.domain.MemberCampaign;
import com.oneblocks.parameter.CampaignListSearchParam;
import com.oneblocks.vo.NSalesVO;

@Repository
public interface MemberCampaignRepository {
	
	List<NSalesVO> getList(CampaignListSearchParam campaignListSearchParam);
	
	String checkDuplicationCampaignName(MemberCampaign memberCampaign);
	
	String checkDuplicationCampaign(MemberCampaign memberCampaign);
	
	void insertMemberCampaignInfo(MemberCampaign memberCampaign);
	
	int setOffMemberCampaign(Map<String,Object> data);

	void updateMemberCampaignInfo(MemberCampaign memberCampaign);
}
