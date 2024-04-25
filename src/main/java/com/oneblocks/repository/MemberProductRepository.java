package com.oneblocks.repository;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
public interface MemberProductRepository {
	
	List<String> getMyOnProductIfByCampaignId(Map<String,String> data) ;

}
