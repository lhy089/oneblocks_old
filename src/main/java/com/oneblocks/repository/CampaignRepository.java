package com.oneblocks.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.oneblocks.parameter.CampaignListSearchParam;
import com.oneblocks.vo.NSalesVO;

@Repository
public interface CampaignRepository {
	
	List<NSalesVO> getList(CampaignListSearchParam campaignListSearchParam);

}
