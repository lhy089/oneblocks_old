package com.oneblocks.domain;

import lombok.Data;

@Data
public class Member {
	
	private String memberId;		// 회원고유값
	private String email;			// 이메일
	private String userId; 			// 회원 ID
	private String password; 		// 비밀번호
	private String userName; 		// 회원명
	private String phoneNumber; 	// 휴대전화
	private String joinDate; 		// 가입일
	private String modifyDate; 		// 수정일
	private String withdrawalDate; 	// 탈퇴날짜
	private String userStatus;		// 사용자 상태 > S:관리자, U:활성화, D:탈퇴
}
