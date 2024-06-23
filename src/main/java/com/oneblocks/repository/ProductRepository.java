package com.oneblocks.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.oneblocks.domain.Product;
import com.oneblocks.parameter.CampaignListSearchParam;

@Repository
public interface ProductRepository {
	
	void insertProductInfo(Product product);
	
	Product getProductByProductId(CampaignListSearchParam campaignListSearchParam);
	
	List<String> getProductIdList(String campaignId);

}
