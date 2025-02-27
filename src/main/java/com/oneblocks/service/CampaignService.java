package com.oneblocks.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oneblocks.domain.Campaign;
import com.oneblocks.domain.CampaignSales;
import com.oneblocks.domain.MemberCampaign;
import com.oneblocks.domain.MemberCampaignHis;
import com.oneblocks.domain.MemberProduct;
import com.oneblocks.domain.Product;
import com.oneblocks.domain.ProductSales;
import com.oneblocks.parameter.CampaignFormParam;
import com.oneblocks.parameter.CampaignListSearchParam;
import com.oneblocks.parameter.CampaignModifyParam;
import com.oneblocks.parameter.SearchParam;
import com.oneblocks.repository.CampaignRepository;
import com.oneblocks.repository.CampaignSalesRepository;
import com.oneblocks.repository.MemberCampaignHisRepository;
import com.oneblocks.repository.MemberCampaignRepository;
import com.oneblocks.repository.MemberProductRepository;
import com.oneblocks.repository.ProductRepository;
import com.oneblocks.repository.ProductSalesRepository;
import com.oneblocks.utils.CommonUtil;
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
	
	public List<NSalesVO> getList(String memberId) {
		return memberCampaignRepository.getList(memberId);
	}
	
	public List<NSalesVO> getCampaignSalesList(List<NSalesVO> salesList, String memberId, SearchParam searchParam) {
		for(int i=0; i<salesList.size(); i++) {
			NSalesVO salesInfo = salesList.get(i);
			
			// 1. price 가 없는 경우 > sales 데이터가 세티되지 않음.
			if(StringUtils.isEmpty(salesInfo.getCampaignPrice())) {
				salesInfo.setCampaignPrice("-9999");
				salesInfo.setTotalSalesQuantity("-9999");
				salesInfo.setTotalSalesRevenue("-9999");
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
				salesInfo.setTotalSalesRevenue("0");
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
	
	public void saveNewCampaign(String memberId, CampaignFormParam campaignFormParam) {

		Campaign campaign = campaignFormParam.getCampaign();
		String hisId = this.getMaxHistoryId(campaign);
		
		// 공용
		// campaign 데이터 있는지 체크 후 없으면 campaign, product insert
		Campaign campaignInfo = this.getCampaignInfo(campaign);
		
		if(campaignInfo == null) {
			this.saveCampaignInfo(campaignFormParam.getCampaign());
			this.saveProductListInfo(campaignFormParam);
			hisId = "1";
		}
		
		// 개인
		// 무조건 insert , member_campaign, member_campaign_his
		this.saveMemberCampaignInfo(campaignFormParam, memberId);
		this.saveMemberCampaignHisInfo(campaignFormParam, memberId, hisId);
		this.saveMemberProductInfo(campaignFormParam, memberId);

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
	
	public void setOffMemberCampaign(String memberId, List<String> campaignIdList) {
		Map<String,Object> data = new HashMap<String,Object>();
		data.put("memberId", memberId);
		data.put("campaignIdList", campaignIdList);
		memberCampaignRepository.setOffMemberCampaign(data);
		memberCampaignHisRepository.setOffMemberCampaignHis(data);
	}
	
	public List<CampaignModifyParam> getProductList(CampaignModifyParam campaignModifyParam) {
		return memberProductRepository.getProductList(campaignModifyParam);
	}
	
	public void modifyCampaign(String memberId, CampaignFormParam campaignFormParam) {
		Campaign campaign = campaignFormParam.getCampaign();
		String hisId = this.getMaxHistoryId(campaign);
		
		this.modifyMemberCampaignInfo(campaignFormParam, memberId);
		this.modifyMemberCampaignHisInfo(campaignFormParam, memberId, hisId);
		this.modifyMemberProductInfo(campaignFormParam, memberId);
	}
	
	public void modifyMemberCampaignInfo(CampaignFormParam campaignFormparam, String memberId) {
		MemberCampaign memberCampaign = new MemberCampaign();
		memberCampaign.setMemberId(memberId);
		memberCampaign.setCampaignId(campaignFormparam.getCampaign().getCampaignId());
		memberCampaign.setOnOffYn("Y");
		memberCampaignRepository.updateMemberCampaignInfo(memberCampaign);	
	}
	
	public void modifyMemberCampaignHisInfo(CampaignFormParam campaignFormParam, String memberId, String hisId) {
		MemberCampaignHis memberCampaignHis = new MemberCampaignHis();
		memberCampaignHis.setMemberId(memberId);
		memberCampaignHis.setCampaignId(campaignFormParam.getCampaign().getCampaignId());
		memberCampaignHis.setHisId(hisId);
		memberCampaignHisRepository.insertMemberCampaignOnHistory(memberCampaignHis);
	}
	
	public void modifyMemberProductInfo(CampaignFormParam campaignFormParam, String memberId) {
		MemberProduct memberProduct = new MemberProduct();
		memberProduct.setMemberId(memberId);
		memberProduct.setCampaignId(campaignFormParam.getCampaign().getCampaignId());
		memberProductRepository.updateMemberProductAllOff(memberProduct);
		
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("memberId", memberId);
		data.put("productIdList", campaignFormParam.getProductIdList());
		if(campaignFormParam.getProductIdList().size() > 0 ) {
			memberProductRepository.updateOnMemberProduct(data);
		}
	}
	
	public void modifyProductStatus(String memberId, MemberProduct memberProduct) {
		memberProduct.setMemberId(memberId);
		memberProduct.setProductId(memberProduct.getProductId());
		memberProduct.setOnOffYn(memberProduct.getOnOffYn()); 
		memberProductRepository.modifyProductStatus(memberProduct);
	}
	
	public List<String> getCampaignUrlList() {
		return campaignRepository.getCampaignUrlList();
		
	}
	
	public void saveProductSalesInfo(JSONObject campaignData) {
		String campaignId = String.valueOf(campaignData.get("id"));
		String yesterday = CommonUtil.getBeforeDate(1); // 어제
	
		// 그저께 재고량
		Map<String, String> stockQuantityMap = this.getStockQuantityFromDayBeforeYesterday(campaignId);
		
		// campaign sales insert
		this.setCampaignSalesInfo(campaignId, campaignData, stockQuantityMap.get(campaignId), yesterday);
		// product sales insert
		this.setProductSalesInfo(campaignId, campaignData, stockQuantityMap, yesterday);		
	}
	
	public Map<String, String> getStockQuantityFromDayBeforeYesterday(String campaignId) {
		Map<String, String> stockQuantityMap = new HashMap<String,String>();
		Map<String, String> data = new HashMap<String,String>();
		data.put("campaignId", campaignId);
		data.put("dayBeforeYesterday", CommonUtil.getBeforeDate(2));
		stockQuantityMap.put(campaignId, productSalesRepository.getDayBeforeYesterdayCampaignStockQuantity(data));
		List<String> saveProductIdList = productRepository.getProductIdList(campaignId);
		
		for(String saveProductId : saveProductIdList) {
			data.put("productId", saveProductId);
			stockQuantityMap.put(saveProductId, productSalesRepository.getDayBeforeYesterdayProductStockQuantity(data));
		}
		return stockQuantityMap;
	}
	
	public void setCampaignSalesInfo(String campaignId, JSONObject campaignData, String lastStockQuantity, String salesId) {
		int stockQuantity = (Integer) campaignData.get("stockQuantity");
		int salesQuantity = 0;
		int revenue = 0;
		
		int campaignSalesPrice = (Integer) campaignData.get("discountedSalePrice");

		CampaignSales campaignSales = new CampaignSales();
		campaignSales.setSalesId(salesId);
		campaignSales.setCampaignId(campaignId);
		campaignSales.setCampaignPrice(campaignSalesPrice);
		if(lastStockQuantity != null) {
			salesQuantity = Integer.parseInt(lastStockQuantity) - stockQuantity;
			revenue = salesQuantity * campaignSalesPrice;
		}
		campaignSales.setSalesQuantity(salesQuantity);
		campaignSales.setRevenue(revenue);
		campaignSales.setStockQuantity(stockQuantity);
		campaignSalesRepository.insertCampaignSalesInfo(campaignSales);
	}
	
	public void setProductSalesInfo(String campaignId, JSONObject campaignData, Map<String, String> stockQuantityMap, String salesId) {
		// 최종 insert date
		List<ProductSales> newProductSalesList = new ArrayList<ProductSales>();
		// 추출데이터 optiondata, supplementdata, campaignid
		
		Boolean optionUsable = (Boolean) campaignData.get("optionUsable");
		Boolean supplementUsable = (Boolean) campaignData.get("supplementProductUsable");		
		int campaignSalesPrice = (Integer) campaignData.get("discountedSalePrice");
		
		if(optionUsable) {
			JSONArray optionData = (JSONArray) campaignData.get("optionCombinations");
			for(int i=0; i<optionData.length(); i++){

				JSONObject item = (JSONObject) optionData.get(i);
				String productId = String.valueOf(item.get("id"));
				String lastStockQuantity = stockQuantityMap.get(productId);
				int salesQuantity = 0;
				int revenue = 0;
				int salesPrice = (Integer) item.get("price");
				int stockQuantity = (Integer) item.get("stockQuantity"); 
				
				productId = String.valueOf(item.get("id"));
				salesPrice = campaignSalesPrice + (Integer) item.get("price");
				stockQuantity = (Integer) item.get("stockQuantity"); 
				
				if(lastStockQuantity != null) {
					salesQuantity = Integer.parseInt(lastStockQuantity) - stockQuantity;
					revenue = salesQuantity * salesPrice;
				}
			
				ProductSales productSalesInfo = new ProductSales();
				productSalesInfo.setPsalesId(salesId);
				productSalesInfo.setProductId(productId);
				productSalesInfo.setSalesPrice(String.valueOf(salesPrice));
				productSalesInfo.setSalesQuantity(salesQuantity);
				productSalesInfo.setRevenue(revenue);
				productSalesInfo.setStockQuantity(stockQuantity);
				newProductSalesList.add(productSalesInfo);
			}
		}
		
		if(supplementUsable) {
			JSONArray supplementData = (JSONArray) campaignData.get("supplementProducts");
			for(int i=0; i<supplementData.length(); i++){ 
				JSONObject item = (JSONObject) supplementData.get(i);
				String productId = String.valueOf(item.get("id"));
				String lastStockQuantity = stockQuantityMap.get(productId);
				int salesQuantity = 0;
				int revenue = 0;
				int salesPrice = (Integer) item.get("price");
				int stockQuantity = (Integer) item.get("stockQuantity"); 
				
				if(lastStockQuantity != null) {
					salesQuantity = Integer.parseInt(lastStockQuantity) - stockQuantity;
					revenue = salesQuantity * salesPrice;
				}
			
				ProductSales productSalesInfo = new ProductSales();
				productSalesInfo.setPsalesId(salesId);
				productSalesInfo.setProductId(productId);
				productSalesInfo.setSalesPrice(String.valueOf(salesPrice));
				productSalesInfo.setSalesQuantity(salesQuantity);
				productSalesInfo.setRevenue(revenue);
				productSalesInfo.setStockQuantity(stockQuantity);
				newProductSalesList.add(productSalesInfo);
			}
		}
		
		if(newProductSalesList.size() > 0) {

			int cnt = productSalesRepository.insertProductSalesInfo(newProductSalesList);
			System.out.println(cnt);
		}
	}
	
}
