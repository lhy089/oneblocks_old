package com.oneblocks.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oneblocks.domain.Member;
import com.oneblocks.domain.MemberCampaign;
import com.oneblocks.repository.CampaignRepository;

import io.swagger.v3.oas.annotations.tags.Tag;

@Service
@Tag(name = "CampaignService", description = "Campaign API")
public class CampaignService {
	
	@Autowired
	private CampaignRepository repository;
	
	
	public List<MemberCampaign> getList(Member member) {
		return repository.getList(member);
	}
	
}
