package com.oneblocks.vo;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SortNSalesList {
	
	public static void sortNSalesListOrderByMemberCampaignNameAsc(List<NSalesVO> list) {
		Collections.sort(list, new MemberCampaignNameComparator());      
	}
	
	public static void sortNSalesListOrderByMemberCampaignNameDesc(List<NSalesVO> list) {
		Collections.sort(list, new MemberCampaignNameComparator().reversed());     
	}
	
	public static void sortNSalesListOrderByCampaignPriceAsc(List<NSalesVO> list) {
		Collections.sort(list, new CampaignPriceComparator());          
	}
	
	public static void sortNSalesListOrderByCampaignPriceDesc(List<NSalesVO> list) {
		Collections.sort(list, new CampaignPriceComparator().reversed());     
	}
	
	public static void sortNSalesListOrderByOnOffYnAsc(List<NSalesVO> list) {
		Collections.sort(list, new OnOffYnComparator());      
	}
	
	public static void sortNSalesListOrderByOnOffYnDesc(List<NSalesVO> list) {
		Collections.sort(list, new OnOffYnComparator().reversed());   
	}
	
	public static void sortNSalesListOrderByTotalSalesQuantityAsc(List<NSalesVO> list) {
		Collections.sort(list, new TotalSalesQuantityComparator());      
	}
	
	public static void sortNSalesListOrderByTotalSalesQuantityDesc(List<NSalesVO> list) {
		Collections.sort(list, new TotalSalesQuantityComparator().reversed());      
	}
	
	public static void sortNSalesListOrderByTotalSalesRevenueAsc(List<NSalesVO> list) {
		Collections.sort(list, new TotalSalesRevenueComparator());      
	}
	
	public static void sortNSalesListOrderByTotalSalesRevenueDesc(List<NSalesVO> list) {
		Collections.sort(list, new TotalSalesRevenueComparator().reversed());    
	}
	
	public static void sortNSalesListOrderByUpdateDateAsc(List<NSalesVO> list) {
		Collections.sort(list, new UpdateDateComparator());      
	}
	
	public static void sortNSalesListOrderByUpdateDateDesc(List<NSalesVO> list) {
		Collections.sort(list, new UpdateDateComparator().reversed());      
	}
}

class MemberCampaignNameComparator implements Comparator<NSalesVO> {    
	@Override    
	public int compare(NSalesVO f1, NSalesVO f2) {        
		return f1.getMemberCampaignName().compareTo(f2.getMemberCampaignName());    
	}
}

class CampaignPriceComparator implements Comparator<NSalesVO> {

	@Override
	public int compare(NSalesVO o1, NSalesVO o2) {
		if(Integer.parseInt(o1.getCampaignPrice()) > Integer.parseInt(o2.getCampaignPrice())) {
			return 1;
		} else if(Integer.parseInt(o1.getCampaignPrice()) < Integer.parseInt(o2.getCampaignPrice())) {
			return -1;
		}
		return 0;
	}
}

class OnOffYnComparator implements Comparator<NSalesVO> {
	@Override    
	public int compare(NSalesVO f1, NSalesVO f2) {        
		return f1.getOnOffYn().compareTo(f2.getOnOffYn());    
	}
}

class TotalSalesQuantityComparator implements Comparator<NSalesVO> {

	@Override
	public int compare(NSalesVO o1, NSalesVO o2) {
		if(Integer.parseInt(o1.getTotalSalesQuantity()) > Integer.parseInt(o2.getTotalSalesQuantity())) {
			return 1;
		} else if(Integer.parseInt(o1.getTotalSalesQuantity()) < Integer.parseInt(o2.getTotalSalesQuantity())) {
			return -1;
		}
		return 0;
	}
}

class TotalSalesRevenueComparator implements Comparator<NSalesVO> {

	@Override
	public int compare(NSalesVO o1, NSalesVO o2) {
		if(Integer.parseInt(o1.getTotalSalesRevenue()) > Integer.parseInt(o2.getTotalSalesRevenue())) {
			return 1;
		} else if(Integer.parseInt(o1.getTotalSalesRevenue()) < Integer.parseInt(o2.getTotalSalesRevenue())) {
			return -1;
		}
		return 0;
	}
}

class UpdateDateComparator implements Comparator<NSalesVO> {
	@Override    
	public int compare(NSalesVO f1, NSalesVO f2) {        
		return f1.getUpdateDate().compareTo(f2.getUpdateDate());    
	}
}