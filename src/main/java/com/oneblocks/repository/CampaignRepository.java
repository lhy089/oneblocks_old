package com.oneblocks.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.oneblocks.domain.Campaign;
import com.oneblocks.domain.MemberCampaign;

@Repository
public interface CampaignRepository {
	
	List<MemberCampaign> getList(MemberCampaign memberCampaign);

}
