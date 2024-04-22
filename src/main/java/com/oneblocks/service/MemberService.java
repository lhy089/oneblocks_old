package com.oneblocks.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oneblocks.domain.Member;
import com.oneblocks.repository.MemberRepository;

@Service
public class MemberService {
	
	@Autowired
	private MemberRepository repository;
	
	public List<Member> getList() {
		List<Member> memberList =  repository.getList();
		return memberList;
	}
	
	public Member get(String memberId) {
		return repository.get(memberId);
	}
	
	public void save(Member member) {
		repository.update(member);
	}
	
	public void delete(String memberId) {
		repository.delete(memberId);
	}

}
