package com.oneblocks.repository;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.oneblocks.domain.ProductSales;
import com.oneblocks.parameter.CampaignListSearchParam;
import com.oneblocks.vo.NSalesVO;
import com.oneblocks.vo.ProductSalesVO;

@Repository
public interface ProductSalesRepository {
	
	NSalesVO getMyProductSalesInfo(Map<String,Object> data);
	
	List<ProductSalesVO> getProductSalesList(CampaignListSearchParam campaignListSearchParam);
	
	List<ProductSalesVO> getProductSalesByProductId(CampaignListSearchParam campaignListSearchParam);
	
	String getDayBeforeYesterdayCampaignStockQuantity(Map<String, String> data);
	
	String getDayBeforeYesterdayProductStockQuantity(Map<String, String> data);
	
	int insertProductSalesInfo(List<ProductSales> productSalesInfo);
}
