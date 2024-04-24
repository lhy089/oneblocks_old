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

import com.oneblocks.configuration.RequestConfiguration;
import com.oneblocks.domain.Member;
import com.oneblocks.domain.MemberCampaign;
import com.oneblocks.parameter.SearchParam;
import com.oneblocks.service.CampaignService;
import com.oneblocks.utils.SearchUtil;

import io.micrometer.common.util.StringUtils;
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
		List<MemberCampaign> myCampaignList = campaignService.getList(member);
		model.addAttribute("myCampaignList", myCampaignList);
		model.addAttribute("searchParam", searchParam);
	}
	
	@PostMapping("/main")
	@ResponseBody
//	@RequestConfiguration
	@Operation(summary = "메인 화면", description = "캠페인 판매량 화면을 조회한다")
	public Map<String, Object> main(@RequestBody SearchParam searchParam, HttpSession session) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		 
		searchParam = SearchUtil.setSalesDate(searchParam);
		Member member = (Member) session.getAttribute("loginMemberInfo");
		List<MemberCampaign> myCampaignList = campaignService.getList(member);
		
		resultMap.put("myCampaignList", myCampaignList); 
		resultMap.put("searchParam", searchParam);
		 
		return resultMap;
	}
	
	@GetMapping("/modal/addForm")
	public void campaignAddForm(Model model) {
		
	}
}
