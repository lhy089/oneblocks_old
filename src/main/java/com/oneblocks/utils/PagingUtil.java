package com.oneblocks.utils;

import java.util.ArrayList;
import java.util.List;

import com.oneblocks.domain.Paging;
import com.oneblocks.vo.NSalesVO;

public class PagingUtil {
	
	public static final int CAMPAIGN_DATE_PER_PAGE = 15;
	public static final int CAMPAIGN_PAGE_COUNT = 10;
	
	private Paging page = new Paging();
	
	public Paging initPaginationInfo(int datePerPage, int pageCount, int pageNum, int allDataCount) {
		
		int totalPage = allDataCount/datePerPage;
    	totalPage = (allDataCount%datePerPage)>0 ? totalPage+1 : totalPage;
    	// 총 페이지 수
    	
    	int pageGroup = pageNum/pageCount ;
    	pageGroup = (pageNum%pageCount)>0 ? pageGroup+1 : pageGroup;
    	// 현재 페이지 그룹 수
    	
    	int last = pageGroup * pageCount;
    	if(last > totalPage) {
    		last = totalPage;
    	}
    	
    	int first =  last != totalPage ? last - (pageCount-1) : last - ((last%pageCount)-1);
    	
    	
    	int prev = (first != 1) ? first-datePerPage : 0;
    	int next = (last < totalPage) ? last+1 : 0;
    	
    	if(totalPage < 1) {
    		first = last;
    	}
    	
    	page.setDatePerPage(datePerPage);
		page.setPageCount(pageCount);
		page.setPageNum(pageNum);
		page.setStartNum(datePerPage*(pageNum-1)+1);
		page.setEndNum(pageNum * datePerPage);
		page.setAllDataCount(allDataCount);
		page.setFirst(first);
		page.setLast(last);
		page.setPrev(prev);
		page.setNext(next);
		
		return page;
	}
	
	public List<NSalesVO> getListForCurrentPage(List<NSalesVO> newsList) {
		List<NSalesVO> listForPaging = new ArrayList<NSalesVO>();
		for(int j=page.getStartNum()-1; j<page.getEndNum(); j++) {
			if(j >= newsList.size()) break;
			listForPaging.add(newsList.get(j));
		}
		return listForPaging;
	}
	
	public void setHomePaging(int pageNum) {
		int dataPerPage = 10;	// 한 화면에 보여지는 데이터 수 
		int startNum = (pageNum-1) * dataPerPage ; // 0
		int endNum = (pageNum) * dataPerPage ; // 10
	}
}
