package com.oneblocks.repository;

import org.springframework.stereotype.Repository;

import com.oneblocks.domain.Product;
import com.oneblocks.parameter.CampaignListSearchParam;

@Repository
public interface ProductRepository {
	
	void insertProductInfo(Product product);
	
	Product getProductByProductId(CampaignListSearchParam campaignListSearchParam);

}
