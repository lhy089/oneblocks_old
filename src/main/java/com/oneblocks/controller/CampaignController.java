package com.oneblocks.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.oneblocks.configuration.RequestConfiguration;
import com.oneblocks.domain.Campaign;
import com.oneblocks.domain.Member;
import com.oneblocks.domain.Paging;
import com.oneblocks.domain.Product;
import com.oneblocks.parameter.CampaignFormParam;
import com.oneblocks.parameter.CampaignListSearchParam;
import com.oneblocks.parameter.SearchParam;
import com.oneblocks.service.CampaignService;
import com.oneblocks.utils.CampaignUtil;
import com.oneblocks.utils.PagingUtil;
import com.oneblocks.utils.SearchUtil;
import com.oneblocks.vo.NSalesVO;
import com.oneblocks.vo.ProductSalesVO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/campaign")
@Tag(name = "Campaign", description = "Campaign API")
public class CampaignController {
	
	@Autowired 
	private CampaignService campaignService;
	
	@GetMapping("/main")
	@RequestConfiguration
	@Operation(summary = "메인 화면 이동", description = "캠페인 판매량 화면을 호출한다")
	public void mainHome(HttpSession session, Model model) {
		
	}
	
	@PostMapping("/main")
	@ResponseBody
//	@RequestConfiguration
	@Operation(summary = "메인 화면", description = "캠페인 판매량 화면을 조회한다")
	public Map<String, Object> main(@RequestBody SearchParam searchParam, HttpSession session) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		 
		/* startDate, endDate 날짜조건 계산 */
		searchParam = SearchUtil.setSalesDate(searchParam);
		
		/*
		 * 사이드 바 캠페인리스트, 메인 리스트 조회 
		 * 내가 등록한 모든 캠페인
		 */
		Member member = (Member) session.getAttribute("loginMemberInfo");
		CampaignListSearchParam campaignListSearchParam = new CampaignListSearchParam();
		campaignListSearchParam.setSearchParam(searchParam);
		campaignListSearchParam.setMemberId(member.getMemberId());
		List<NSalesVO> myCampaignList = campaignService.getList(campaignListSearchParam);
		
		List<NSalesVO> salesList = campaignService.getCampaignSalesList(myCampaignList, member.getMemberId(), searchParam);
		// 정렬
		
		// 페이징
        PagingUtil paging = new PagingUtil();
        // 한 화면에 보여질 데이터 수, 한 화면에 보여지는 페이지 수, 현재 페이지, 전체 데이터 갯수
        Paging page = paging.initPaginationInfo(10, 10, searchParam.getPageNum(), myCampaignList.size());
        List<NSalesVO> newsListForPaging = paging.getListForCurrentPage(salesList);
		
        resultMap.put("myCampaignList", myCampaignList); 
		resultMap.put("searchParam", searchParam);
		resultMap.put("paging", page);
		resultMap.put("salesList", newsListForPaging);
		 
		return resultMap;
	}
	
	@GetMapping("/modal/addForm")
	public void campaignAddForm(Model model) {
		
	}

	@GetMapping("/get")
	@ResponseBody
	public Map<String, Object> getProductInfo(String campaignUrl) {

		Map<String, Object> resultMap = new HashMap<String, Object>();

		JSONObject campaignData = CampaignUtil.getCampaignDataByCampaignUrl(campaignUrl);
		Campaign campaignInfo = CampaignUtil.getCampaignInfo(campaignData);
		List<Product> optionList = CampaignUtil.getOptionList(campaignData);
		List<Product> supplementList = CampaignUtil.getSupplementList(campaignData);

		resultMap.put("productInfo", campaignInfo); 
		resultMap.put("optionList", optionList);
		resultMap.put("supplementList", supplementList);

		return resultMap;
	}
	
	@PostMapping("/product")
	@ResponseBody
//	@RequestConfiguration
	@Operation(summary = "프로덕트 화면", description = "옵션 판매량 화면을 조회한다")
	public Map<String, Object> product(@RequestBody CampaignListSearchParam campaignListSearchParam, HttpSession session) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		 
		/* startDate, endDate 날짜조건 계산 */
		SearchParam searchParam = SearchUtil.setSalesDate(campaignListSearchParam.getSearchParam());
		
		/*
		 * 사이드 바 캠페인리스트, 메인 리스트 조회 
		 * 내가 등록한 모든 캠페인
		 */
		Member member = (Member) session.getAttribute("loginMemberInfo");
		campaignListSearchParam.setSearchParam(searchParam);
		campaignListSearchParam.setMemberId(member.getMemberId());
		
		List<Map<String,String>> onDateList = campaignService.getMyCampaignOnPeriod(campaignListSearchParam);
		campaignListSearchParam.setDateList(onDateList);
		
		List<ProductSalesVO> salesList = campaignService.getProductSalesList(campaignListSearchParam);
		Campaign campaign = campaignService.getCampaignByCampaignId(campaignListSearchParam);
		
		PagingUtil paging = new PagingUtil();
        // 한 화면에 보여질 데이터 수, 한 화면에 보여지는 페이지 수, 현재 페이지, 전체 데이터 갯수
        Paging page = paging.initPaginationInfo(10, 10, searchParam.getPageNum(), salesList.size());
        List<ProductSalesVO> newsListForPaging = paging.getListForCurrentPage2(salesList);
		
        resultMap.put("campaign", campaign);
		resultMap.put("salesList", newsListForPaging);
		resultMap.put("searchParam", searchParam);
		resultMap.put("paging", page);
		 
		return resultMap;
	}
	
	@PostMapping("/productDetail")
	@ResponseBody
//	@RequestConfiguration
	@Operation(summary = "프로덕트 화면", description = "옵션 판매량 화면을 조회한다")
	public Map<String, Object> productDetail(@RequestBody CampaignListSearchParam campaignListSearchParam, HttpSession session) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		 
		/* startDate, endDate 날짜조건 계산 */
		SearchParam searchParam = SearchUtil.setSalesDate(campaignListSearchParam.getSearchParam());
		
		/*
		 * 사이드 바 캠페인리스트, 메인 리스트 조회 
		 * 내가 등록한 모든 캠페인
		 */
		Member member = (Member) session.getAttribute("loginMemberInfo");
		campaignListSearchParam.setSearchParam(searchParam);
		campaignListSearchParam.setMemberId(member.getMemberId());
		
		List<Map<String,String>> onDateList = campaignService.getMyCampaignOnPeriod(campaignListSearchParam);
		campaignListSearchParam.setDateList(onDateList);
		
		List<ProductSalesVO> salesList = campaignService.getProductSalesByProductId(campaignListSearchParam);
		Product product = campaignService.getProductByProductId(campaignListSearchParam);
		
		PagingUtil paging = new PagingUtil();
        // 한 화면에 보여질 데이터 수, 한 화면에 보여지는 페이지 수, 현재 페이지, 전체 데이터 갯수
        Paging page = paging.initPaginationInfo(10, 10, searchParam.getPageNum(), salesList.size());
        List<ProductSalesVO> newsListForPaging = paging.getListForCurrentPage2(salesList);
		
        resultMap.put("product", product);
		resultMap.put("salesList", newsListForPaging);
		resultMap.put("searchParam", searchParam);
		resultMap.put("paging", page);
		 
		return resultMap;
	}
	
	/*
	  * 캠페인 추가
	  */
	 @PostMapping("/new")
	 @Operation(summary = "캠페인추가", description = "캠페인추가")
	 @ResponseBody
	 public Map<String, Object> saveNewCampaign(@RequestBody CampaignFormParam campaignFormparam, HttpSession session) {
		Member member = (Member) session.getAttribute("loginMemberInfo");
		Map<String, Object> resultMap = new HashMap<String, Object>();
	
		// 이미 등록된 캠페인명 : DUPLICATIONNAME : memberId, memberCampaignName 
		// or 이미 등록된 캠페인 : DUPLICATIONCAMPAIGN : memberId, campaignId
		String dupleChk = campaignService.checkDuplicationCampaignName(member.getMemberId(), campaignFormparam);
		if(!"N".equals(dupleChk)) {
			resultMap.put("resultCd", dupleChk);
			return resultMap;
		}
		
		campaignService.saveNewCampaign(member.getMemberId(), campaignFormparam);

		resultMap.put("resultCd", "SUCCESS");
		return resultMap;
	}
	 
	 @PostMapping("/delete")
	 @ResponseBody
	 public Map<String, Object> updateOffMyCampaign(@RequestBody List<String> campaignIdList, HttpSession session) {
		Member member = (Member) session.getAttribute("loginMemberInfo");
		Map<String, Object> resultMap = new HashMap<String, Object>();
		campaignService.setOffMemberCampaign(member.getMemberId(), campaignIdList);
		resultMap.put("resultCd", "SUCCESS");
		return resultMap;
	 }

	 @GetMapping("/modal/modifyForm")
	 public void campaignModifyForm(Model model) {

	 }
}
