package com.oneblocks.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oneblocks.configuration.exception.BaseException;
import com.oneblocks.configuration.http.BaseResponseCode;
import com.oneblocks.domain.Member;
import com.oneblocks.service.MemberService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/member")
@Tag(name = "Member", description = "Member API")
public class MemberController {

	@Autowired
	private MemberService memberService;
	
	@Operation(summary = "회원 리스트 조회", description = "모든 회원의 리스트를 조회한다.")
	@GetMapping
	public List<Member> getList() {
		return memberService.getList();
	}
	
	@Operation(summary = "특정 회원 조회", description = "memberId로 특정 회원을 조회한다.")
    @Parameter(name = "memberId", description = "멤버의 고유번호")
	@GetMapping("/{memberId}")
	public Member get(@PathVariable String memberId) {
		return memberService.get(memberId);
	}
	
	@PostMapping("/save")
	public void save(Member member) {
		memberService.save(member);
	}
	
	@PostMapping("/delete/{memberId}")
	public void delete(String memberId) {
		memberService.delete(memberId);
	}
}
