package com.oneblocks.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oneblocks.domain.Campaign;
import com.oneblocks.domain.MemberCampaign;
import com.oneblocks.domain.MemberCampaignHis;
import com.oneblocks.domain.MemberProduct;
import com.oneblocks.domain.Product;
import com.oneblocks.parameter.CampaignFormParam;
import com.oneblocks.parameter.CampaignListSearchParam;
import com.oneblocks.parameter.SearchParam;
import com.oneblocks.repository.CampaignRepository;
import com.oneblocks.repository.CampaignSalesRepository;
import com.oneblocks.repository.MemberCampaignHisRepository;
import com.oneblocks.repository.MemberCampaignRepository;
import com.oneblocks.repository.MemberProductRepository;
import com.oneblocks.repository.ProductRepository;
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
	private ProductRepository productRepository;
	
	@Autowired
	private MemberCampaignRepository memberCampaignRepository;
	
	@Autowired
	private MemberCampaignHisRepository memberCampaignHisRepository;
	
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
		return memberCampaignHisRepository.getMyCampaignOnPeriod(campaignListSearchParam);
	}
	
	public List<String> getMyOnProductIfByCampaignId(String memberId, String campaignId) {
		MemberProduct memberProduct = new MemberProduct();
		memberProduct.setMemberId(memberId);
		memberProduct.setCampaignId(campaignId);
		return memberProductRepository.getMyOnProductIfByCampaignId(memberProduct);
	}
	
	public NSalesVO getMyCampaignSalesInfo(String campaignId, List<Map<String,String>> onDataList) {
		CampaignListSearchParam campaignListSearchParam = new CampaignListSearchParam();
		campaignListSearchParam.setCampaignId(campaignId);
		campaignListSearchParam.setDateList(onDataList);
		return campaignSalesRepository.getMyCampaignSalesInfo(campaignListSearchParam);
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
	
	public String checkDuplicationCampaignName(String memberId, CampaignFormParam campaignFormparam) {
		String result = "N";
		MemberCampaign memberCampaign = new MemberCampaign();
		memberCampaign.setMemberId(memberId);
		memberCampaign.setMemberCampaignName(campaignFormparam.getMemberCampaignName());
		memberCampaign.setCampaignId(campaignFormparam.getCampaign().getCampaignId());
		if("Y".equals(memberCampaignRepository.checkDuplicationCampaignName(memberCampaign))) {
			result = "DUPLECATIONNAME";
		}else if("Y".equals(memberCampaignRepository.checkDuplicationCampaign(memberCampaign))) {
			result = "DUPLECATIONCAMPAIGN";
		}
		return result;
	}
	
	public void saveNewCampaign(String memberId, CampaignFormParam campaignFormparam) {

		Campaign campaign = campaignFormparam.getCampaign();
		String hisId = this.getMaxHistoryId(campaign);
		
		// 공용
		// campaign 데이터 있는지 체크 후 없으면 campaign, product insert
		Campaign campaignInfo = this.getCampaignInfo(campaign);
		
		if(campaignInfo == null) {
			this.saveCampaignInfo(campaignFormparam.getCampaign());
			this.saveProductListInfo(campaignFormparam);
			hisId = "1";
		}
		
		// 개인
		// 무조건 insert , member_campaign, member_campaign_his
		this.saveMemberCampaignInfo(campaignFormparam, memberId);
		this.saveMemberCampaignHisInfo(campaignFormparam, memberId, hisId);
		this.saveMemberProductInfo(campaignFormparam, memberId);

	}
	
	public Campaign getCampaignInfo(Campaign campaign) {
		return campaignRepository.getCampaignInfo(campaign);
	}
	
	public String getMaxHistoryId(Campaign campaign) {
		 return memberCampaignHisRepository.getMaxHistoryId(campaign.getCampaignId());
	}
	
	public void saveCampaignInfo(Campaign campaign) {
		campaignRepository.insertCampaignInfo(campaign);
		
	}
	
	public void saveProductListInfo(CampaignFormParam campaignFormparam) {
		
		List<Product> optionList = campaignFormparam.getOptionList();
		List<Product> supplemnetList = campaignFormparam.getSupplementList();
		int productNo = 0;
		
		for(Product option : optionList) {
			option.setCampaignId(campaignFormparam.getCampaign().getCampaignId());
			option.setProductNo(String.valueOf(productNo++));
			option.setProductFlag("O");
			productRepository.insertProductInfo(option);
		}
		
		for(Product supplement : supplemnetList) {
			supplement.setCampaignId(campaignFormparam.getCampaign().getCampaignId());
			supplement.setProductNo(String.valueOf(productNo++));
			supplement.setProductFlag("S");
			productRepository.insertProductInfo(supplement);
			
		}
	}
	
	public void saveMemberCampaignInfo(CampaignFormParam campaignFormparam, String memberId) {
		Campaign campaign = campaignFormparam.getCampaign();
		MemberCampaign memberCampaign = new MemberCampaign();
		memberCampaign.setMemberCampaignName(campaignFormparam.getMemberCampaignName());
		memberCampaign.setMemberId(memberId);
		memberCampaign.setCampaignId(campaign.getCampaignId());
		memberCampaign.setOnOffYn("Y");
		
		memberCampaignRepository.insertMemberCampaignInfo(memberCampaign);	
	}
	
	public void saveMemberCampaignHisInfo(CampaignFormParam campaignFormparam, String memberId, String hisId) { 
		MemberCampaignHis memberCampaignHis = new MemberCampaignHis();
		memberCampaignHis.setMemberId(memberId);
		memberCampaignHis.setCampaignId(campaignFormparam.getCampaign().getCampaignId());
		memberCampaignHis.setHisId(hisId);
		
		memberCampaignHisRepository.insertMemberCampaignOnHistory(memberCampaignHis);
	}
	
	public void saveMemberProductInfo(CampaignFormParam campaignFormparam, String memberId) {
		Campaign campaign = campaignFormparam.getCampaign();
		List<Product> optionList = campaignFormparam.getOptionList();
		List<Product> supplementList = campaignFormparam.getSupplementList();
		List<String> productIdList = campaignFormparam.getProductIdList();
		
		List<Product> productList = new ArrayList<Product>();
		productList.addAll(optionList);
		productList.addAll(supplementList);
		
		if(productList.size() != 0) {
			List<MemberProduct> memberProductList = new ArrayList<MemberProduct>();
			
			for(int i=0; i<productList.size(); i++) {
				MemberProduct memberProduct = new MemberProduct();
				String productId = productList.get(i).getProductId();
				memberProduct.setMemberId(memberId);
				memberProduct.setCampaignId(campaign.getCampaignId());
				memberProduct.setProductId(productId);
				memberProduct.setOnOffYn(productIdList.contains(productId) ? "Y" : "N");
				memberProductList.add(memberProduct);
			}
			memberProductRepository.insertMemberProductInfo(memberProductList);
		}
	}
	
	public Product getProductByProductId(CampaignListSearchParam campaignListSearchParam) {
		return productRepository.getProductByProductId(campaignListSearchParam);
	}
	
}
