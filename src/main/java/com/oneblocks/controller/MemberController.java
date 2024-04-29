package com.oneblocks.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.oneblocks.configuration.exception.BaseException;
import com.oneblocks.configuration.http.BaseResponseCode;
import com.oneblocks.domain.Member;
import com.oneblocks.parameter.MemberLoginParam;
import com.oneblocks.service.MemberService;

import io.micrometer.common.util.StringUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;

@Controller
@Tag(name = "Member", description = "Member API")
public class MemberController {

	@Autowired
	private MemberService memberService;
	
	@GetMapping
	@Operation(summary = "로그인 화면", description = "로그인 화면을 호출한다.")
	public String loginForm(Model model) {
		return "/member/login";
	}
	
	@PostMapping("/login")
	@ResponseBody
	@Operation(summary = "로그인", description = "로그인을 한다.")
	public Map<String, Object> doLogin(@RequestBody MemberLoginParam memberLoginParam, HttpSession session, Model model) {
		String forward = "FAILED";	
		Map<String, Object> resultMap = new HashMap<String, Object>();
		if(StringUtils.isEmpty(memberLoginParam.getEmail())) {
			throw new BaseException(BaseResponseCode.DATA_IS_NULL, new String[] {"email"});
		}else if(StringUtils.isEmpty(memberLoginParam.getPassword())) {
			throw new BaseException(BaseResponseCode.DATA_IS_NULL, new String[] {"password"});
		}
		
		Member memberInfo = memberService.get(memberLoginParam);
		
		if(memberInfo != null) {
			session.setAttribute("loginMemberInfo", memberInfo);
			forward = "SUCCESS";
		}else {
			forward = "FAILED";
		}
		resultMap.put("resultCode", forward);
		return resultMap;
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session,Model model) {
		session.invalidate();
		return "redirect:/";
	}
}
