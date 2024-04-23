package com.oneblocks.parameter;

import lombok.Data;

@Data
public class MemberLoginParam {
	private String email;			// 이메일
	private String password; 		// 비밀번호
}
