package com.oneblocks.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oneblocks.domain.Member;
import com.oneblocks.parameter.MemberLoginParam;
import com.oneblocks.repository.MemberRepository;

@Service
public class MemberService {
	
	@Autowired
	private MemberRepository repository;
	public Member get(MemberLoginParam memberLoginParam) {
		return repository.get(memberLoginParam);
	}
}
