package com.oneblocks.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Controller
@RequestMapping("/campaign")
@Tag(name = "Campaign", description = "Campaign API")
public class CampaignController {
	
	@GetMapping("/left")
	@ResponseBody
	@Operation(summary = "캠페인 리스트 조회", description = "모든 캠페인의 리스트를 조회한다.")
	public void list(Model model) {
		
	}
	
	@GetMapping("/main")
	@Operation(summary = "메인 화면", description = "캠페인 판매량 화면을 조회한다")
	public void main(Model model) {
		List<Map<String,String>> myCampaignList = new ArrayList<Map<String,String>>();
		Map<String,String> map = new HashMap<String, String>();
		map.put("onOffYn", "Y");
		map.put("campaignId", "1234");
		map.put("memberCampaignName", "캠페인캠페인111");
		myCampaignList.add(map);
		myCampaignList.add(map);
		myCampaignList.add(map);
		myCampaignList.add(map);
		myCampaignList.add(map);
		myCampaignList.add(map);
		myCampaignList.add(map);
		myCampaignList.add(map);
		myCampaignList.add(map);
		myCampaignList.add(map);
		myCampaignList.add(map);
		myCampaignList.add(map);Map<String,String> map1 = new HashMap<String, String>();
		map1.put("onOffYn", "N");
		map1.put("campaignId", "1234");
		map1.put("memberCampaignName", "캠페인캠페인111");
		myCampaignList.add(map1);
		myCampaignList.add(map1);
		
		model.addAttribute("myCampaignList", myCampaignList);
	}
}
