package com.oneblocks.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oneblocks.domain.Campaign;
import com.oneblocks.parameter.CampaignListSearchParam;
import com.oneblocks.parameter.SearchParam;
import com.oneblocks.repository.CampaignRepository;
import com.oneblocks.repository.CampaignSalesRepository;
import com.oneblocks.repository.MemberCampaignRepository;
import com.oneblocks.repository.MemberProductRepository;
import com.oneblocks.repository.ProductSalesRepository;
import com.oneblocks.vo.NSalesVO;
import com.oneblocks.vo.ProductSalesVO;

import io.micrometer.common.util.StringUtils;
import io.swagger.v3.oas.annotations.tags.Tag;

@Service
@Tag(name = "CampaignService", description = "Campaign API")
public class CampaignService {
	
	@Autowired
	private CampaignRepository campaignRepository;
	
	@Autowired
	private MemberCampaignRepository memberCampaignRepository;
	
	@Autowired
	private MemberProductRepository memberProductRepository;
	
	@Autowired
	private CampaignSalesRepository campaignSalesRepository;
	
	@Autowired
	private ProductSalesRepository productSalesRepository;
	
	public List<NSalesVO> getList(CampaignListSearchParam campaignListSearchParam) {
		return memberCampaignRepository.getList(campaignListSearchParam);
	}
	
	public List<NSalesVO> getCampaignSalesList(List<NSalesVO> salesList, String memberId, SearchParam searchParam) {
		for(int i=0; i<salesList.size(); i++) {
			NSalesVO salesInfo = salesList.get(i);
			
			// 1. price 가 없는 경우 > sales 데이터가 세티되지 않음.
			if(StringUtils.isEmpty(salesInfo.getCampaignPrice())) {
				salesInfo.setCampaignPrice("-");
				salesInfo.setTotalSalesQuantity("-");
				salesInfo.setTotalSalesRevenue("-");
				salesInfo.setUpdateDate("-");
				continue;
			}
			
			CampaignListSearchParam campaignListSearchParam = new CampaignListSearchParam();
			campaignListSearchParam.setSearchParam(searchParam);
			campaignListSearchParam.setMemberId(memberId);
			campaignListSearchParam.setCampaignId(salesInfo.getCampaignId());
			
			List<Map<String,String>> onDateList = this.getMyCampaignOnPeriod(campaignListSearchParam);
			if(onDateList.size() == 0) {
				salesInfo.setCampaignPrice("0");
				salesInfo.setTotalSalesQuantity("0");
				continue;
			}
			
			List<String> myProductIdList = this.getMyOnProductIfByCampaignId(memberId, salesInfo.getCampaignId());
			if(myProductIdList.size() == 0) {
				NSalesVO campaignSalesInfo = this.getMyCampaignSalesInfo(salesInfo.getCampaignId(), onDateList);
				if(campaignSalesInfo != null) {
					salesInfo.setTotalSalesQuantity(campaignSalesInfo.getTotalSalesQuantity());
					salesInfo.setTotalSalesRevenue(campaignSalesInfo.getTotalSalesRevenue());
				}else {
					salesInfo.setTotalSalesQuantity("0");
					salesInfo.setTotalSalesRevenue("0");
				}
			}else {
				NSalesVO productSalesInfo = this.getMyProductSalesInfo(myProductIdList, onDateList);
				if(productSalesInfo != null) {
					salesInfo.setTotalSalesQuantity(productSalesInfo.getTotalSalesQuantity());
					salesInfo.setTotalSalesRevenue(productSalesInfo.getTotalSalesRevenue());
				}else {
					salesInfo.setTotalSalesQuantity("0");
					salesInfo.setTotalSalesRevenue("0");
				}
			}
		}
		
		return salesList;
	}
	
	public List<Map<String,String>> getMyCampaignOnPeriod(CampaignListSearchParam campaignListSearchParam) {
		return memberCampaignRepository.getMyCampaignOnPeriod(campaignListSearchParam);
	}
	
	public List<String> getMyOnProductIfByCampaignId(String memberId, String campaignId) {
		Map<String,String> data = new HashMap<String,String>();
		data.put("memberId", memberId);
		data.put("campaignId", campaignId);
		return memberProductRepository.getMyOnProductIfByCampaignId(data);
	}
	
	public NSalesVO getMyCampaignSalesInfo(String campaignId, List<Map<String,String>> onDataList) {
		Map<String,Object> data = new HashMap<String,Object>();
		data.put("campaignId", campaignId);
		data.put("dateList", onDataList);
		return campaignSalesRepository.getMyCampaignSalesInfo(data);
	}
	
	public NSalesVO getMyProductSalesInfo(List<String> productIdList, List<Map<String,String>> dateList) {
		Map<String,Object> data = new HashMap<String,Object>();
		data.put("productIdList", productIdList);
		data.put("dateList", dateList);
		return productSalesRepository.getMyProductSalesInfo(data);
	}
	
	public List<ProductSalesVO> getProductSalesList(CampaignListSearchParam campaignListSearchParam) {
		return productSalesRepository.getProductSalesList(campaignListSearchParam);
	}
	
	public Campaign getCampaignByCampaignId(CampaignListSearchParam campaignListSearchParam) {
		return campaignRepository.getCampaignByCampaignId(campaignListSearchParam);
	}
	
	public List<ProductSalesVO> getProductSalesByProductId(CampaignListSearchParam campaignListSearchParam) {
		return productSalesRepository.getProductSalesByProductId(campaignListSearchParam);
	}
	
}
