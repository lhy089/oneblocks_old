package com.oneblocks.repository;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.oneblocks.domain.MemberProduct;
import com.oneblocks.parameter.CampaignModifyParam;

@Repository
public interface MemberProductRepository {
	
	List<String> getMyOnProductIfByCampaignId(MemberProduct memberProduct) ;
	
	void insertMemberProductInfo(List<MemberProduct> memberProductList);
	
	List<CampaignModifyParam> getProductList(CampaignModifyParam campaignModifyParam);
	
	void updateMemberProductAllOff(MemberProduct memberProduct);
	
	int updateOnMemberProduct(Map<String,Object> data);
	
	void modifyProductStatus(MemberProduct memberProduct);

}
