package com.oneblocks.repository;

import org.springframework.stereotype.Repository;

import com.oneblocks.domain.Member;
import com.oneblocks.parameter.MemberLoginParam;

@Repository
public interface MemberRepository {
	
	Member get(MemberLoginParam memberLoginParam);

}
