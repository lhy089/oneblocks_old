package com.oneblocks.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.oneblocks.domain.Member;

@Repository
public interface MemberRepository {
	
	List<Member> getList();
	
	Member get(String memberId);
	
	void update(Member member);
	
	void delete(String memberId);

}
