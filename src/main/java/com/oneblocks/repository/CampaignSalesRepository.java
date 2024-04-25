package com.oneblocks.repository;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.oneblocks.vo.NSalesVO;

@Repository
public interface CampaignSalesRepository {
	
	NSalesVO getMyCampaignSalesInfo(Map<String,Object> data);

}
