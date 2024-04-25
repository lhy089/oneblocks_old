package com.oneblocks.domain;

import lombok.Data;

@Data
public class Paging {
	
	private int allDataCount;		// 전체 데이터 수
	private int datePerPage;		// 한 페이지에서 보여 줄 데이터 수
	private int pageCount;			// 한 화면에 보여질 페이지 그룹 수
	private int pageNum;			// 현재 페이지 번호
	private int startNum;			// 현재 페이지에 뿌려질 데이터 첫 번쨰 번호
	private int endNum;				// 현재 페이지에 뿌려질 데이터의 마지막 번호
	private int totalPage;			// 총 페이지 수
	private int pageGroup;			// 현재 페이지의 그룹 순서
	private int first;				// 현재 페이지 그룹의(현재 화면의) 첫 번쨰 페이지 번호
	private int last;				// 현재 페이지 그룹의(현재 화면의) 마지막 페이지 번호
	private int prev;				// 이전 페이지 그룹의 첫 번쨰 번호( < 클릭 시 )
	private int next;				// 다음 페이지 그룹의 첫 번쨰 번호( > 클릭 시 )
	
}
