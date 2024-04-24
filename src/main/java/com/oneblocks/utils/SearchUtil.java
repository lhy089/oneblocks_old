package com.oneblocks.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.oneblocks.parameter.SearchParam;

public class SearchUtil {
	
	public static SearchParam setSalesDate(SearchParam searchParam) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String today = sdf.format(new Date());

		String flag = searchParam.getFlag(); // prev, next, select, date
		String dateFlag = searchParam.getDateFlag();
		String startDate = searchParam.getStartDate();
		String endDate = searchParam.getEndDate();
		
		Calendar c = Calendar.getInstance();
		c.add(c.DATE, -1);
		String yesterday = sdf.format(c.getTime());
		Calendar cal = Calendar.getInstance();
		
		if("select".equals(flag)) {
			//어제
			if("yesterday".equals(dateFlag)) {
				startDate = yesterday;
				endDate = yesterday;
			}
			
			// 이번주 월요일
			if("thisWeek".equals(dateFlag)) {
				cal.set(Calendar.DAY_OF_WEEK,Calendar.MONDAY);
				String thisMonday = sdf.format(cal.getTime());
				startDate = thisMonday;
				endDate = yesterday;
			}
			// 이번달 1일
			if("thisMonth".equals(dateFlag)) {
				cal.set(Calendar.DAY_OF_MONTH,1); // 1일
				String thisMonth1stDay = sdf.format(cal.getTime());
				startDate = thisMonth1stDay;
				endDate = yesterday;
			}

			// 지난주 월요일
			if("lastWeek".equals(dateFlag)) {
				cal.add(Calendar.DATE, -7);
				cal.set(Calendar.DAY_OF_WEEK,Calendar.MONDAY);
				String lsatMonday = sdf.format(cal.getTime());
				
				cal.add(Calendar.DATE, +6);
				String lastSunday = sdf.format(cal.getTime());
				startDate = lsatMonday;
				endDate = lastSunday;
			}
			
			// 지난달 1일
			if("lastMonth".equals(dateFlag)) {
				cal.add ( cal.MONTH, -1 ); // 이전달
				cal.set(Calendar.DAY_OF_MONTH,1); // 1일
				String lastMonth1stDay = sdf.format(cal.getTime());
				cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
				String lastMonthLastDay = sdf.format(cal.getTime());
				startDate = lastMonth1stDay;
				endDate = lastMonthLastDay;
			}
			
			// 7일전
			if("sevenDays".equals(dateFlag)) {
				cal = Calendar.getInstance();
				cal.add(cal.DATE, -7);
				String before7Day = sdf.format(cal.getTime());
				startDate = before7Day;
				endDate = yesterday;
			}
			
			// 30일전
			if("thirtyDays".equals(dateFlag)) {
				cal = Calendar.getInstance();
				cal.add(cal.DATE, -30);
				String before30Day = sdf.format(cal.getTime());
				startDate = before30Day;
				endDate = yesterday;
			}
			
			searchParam.setStartDate(startDate);
			searchParam.setEndDate(endDate);
		}
//		if(dateFlag == null & endDate == null ) {
//			dateFlag = "yesterday";
//		}
//		if(startDate == null || "".equals(startDate)) {
//			startDate = today;
//		}
		return searchParam;
	}

}
