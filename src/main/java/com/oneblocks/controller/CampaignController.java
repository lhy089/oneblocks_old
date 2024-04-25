package com.oneblocks.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.oneblocks.domain.Member;
import com.oneblocks.domain.Paging;
import com.oneblocks.parameter.CampaignListSearchParam;
import com.oneblocks.parameter.SearchParam;
import com.oneblocks.service.CampaignService;
import com.oneblocks.utils.PagingUtil;
import com.oneblocks.utils.SearchUtil;
import com.oneblocks.vo.NSalesVO;

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
//	@RequestConfiguration
	@Operation(summary = "메인 화면", description = "캠페인 판매량 화면을 조회한다")
	public void mainHome(HttpSession session, Model model) {
		SearchParam searchParam = new SearchParam();
		searchParam.setDateFlag("yesterday");
		searchParam.setFlag("select");
		searchParam = SearchUtil.setSalesDate(searchParam);
		
		Member member = (Member) session.getAttribute("loginMemberInfo");
//		List<MemberCampaign> myCampaignList = campaignService.getList(member);
//		model.addAttribute("myCampaignList", myCampaignList);
		model.addAttribute("searchParam", searchParam);
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

//		JSONObject campaignData = campaignService.getCampaignDataByCampaignUrl(campaignUrl);
//		Campaign campaignInfo = campaignService.getCampaignInfo(campaignData);
//		List<Product> optionList = campaignService.getOptionList(campaignData);
//		List<Product> supplementList = campaignService.getSupplementList(campaignData);

//		resultMap.put("productInfo", campaignInfo); 
//		resultMap.put("optionList", optionList);
//		resultMap.put("supplementList", supplementList);

		return resultMap;
	}
}
