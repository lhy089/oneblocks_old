package com.oneblocks.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oneblocks.domain.Member;
import com.oneblocks.service.MemberService;

@RestController
@RequestMapping("/member")
public class MemberController {

	@Autowired
	private MemberService memberService;
	
	@GetMapping
	public List<Member> getList() {
		return memberService.getList();
	}
	
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
