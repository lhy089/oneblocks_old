package com.oneblocks.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.oneblocks.configuration.RequestConfiguration;
import com.oneblocks.domain.Member;
import com.oneblocks.domain.MemberCampaign;
import com.oneblocks.service.CampaignService;

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
	@Operation(summary = "메인 화면", description = "캠페인 판매량 화면을 조회한다")
	public void main(HttpSession session, Model model) {
		Member member = (Member) session.getAttribute("loginMemberInfo");
		List<MemberCampaign> myCampaignList = campaignService.getList(member);
		model.addAttribute("myCampaignList", myCampaignList);
	}
}
