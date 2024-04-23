package com.oneblocks.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oneblocks.domain.MemberCampaign;
import com.oneblocks.repository.CampaignRepository;

import io.swagger.v3.oas.annotations.tags.Tag;

@Service
@Tag(name = "CampaignService", description = "Campaign API")
public class CampaignService {
	
	@Autowired
	private CampaignRepository repository;
	
	
	public List<MemberCampaign> getList() {
		MemberCampaign memberCampaign = new MemberCampaign();
		memberCampaign.setMemberId("M12345678");
		return repository.getList(memberCampaign);
	}
	
}
