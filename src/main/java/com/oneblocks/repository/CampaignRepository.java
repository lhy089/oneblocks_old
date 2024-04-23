package com.oneblocks.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.oneblocks.domain.Member;
import com.oneblocks.domain.MemberCampaign;

@Repository
public interface CampaignRepository {
	
	List<MemberCampaign> getList(Member member);

}
