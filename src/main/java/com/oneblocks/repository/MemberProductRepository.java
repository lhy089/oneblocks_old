package com.oneblocks.repository;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.oneblocks.domain.MemberProduct;

@Repository
public interface MemberProductRepository {
	
	List<String> getMyOnProductIfByCampaignId(MemberProduct memberProduct) ;
	
	void insertMemberProductInfo(List<MemberProduct> memberProductList);

}
