package com.oneblocks.utils;

import java.text.ParseException;
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
		
		if("select".equals(flag)) {
			Calendar c = Calendar.getInstance();
			c.add(c.DATE, -1);
			String yesterday = sdf.format(c.getTime());
			Calendar cal = Calendar.getInstance();
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
				cal.add(cal.MONTH, -1);
				String before30Day = sdf.format(cal.getTime());
				startDate = before30Day;
				endDate = yesterday;
			}
			
		} else if("prev".equals(flag)) {
			Calendar cal = Calendar.getInstance();
			Calendar c = Calendar.getInstance();
			try {
				cal.setTime(sdf.parse(startDate));  // startDate를 calendar로 c는 startdate
				c.setTime(sdf.parse(startDate));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			cal.add(cal.DATE, -1);
			String yesterday = sdf.format(cal.getTime());
			
			//어제
			if("yesterday".equals(dateFlag)) {
				startDate = yesterday; 	// startDate의 어제
				endDate = yesterday;	// startDate의 어제
			}
			
			// 이번주 월요일
			if("thisWeek".equals(dateFlag)) {
				c.add(c.DATE, -7);	// startDate의 7일 전
				String thisMonday = sdf.format(c.getTime());
				startDate = thisMonday;
				endDate = yesterday;
			}
			// 이번달 1일
			if("thisMonth".equals(dateFlag)) {
				c.add(c.MONTH, -1 ); // startDate의 지난 달
				c.set(Calendar.DAY_OF_MONTH,1); // 해당 월의 1일로 변경
				String thisMonth1stDay = sdf.format(c.getTime());
				startDate = thisMonth1stDay;
				endDate = yesterday;
			}

			// 지난주 월요일
			if("lastWeek".equals(dateFlag)) {
				c.add(c.DATE, -7);
				c.set(Calendar.DAY_OF_WEEK,Calendar.MONDAY);
				String lsatMonday = sdf.format(c.getTime());
				
//				cal.add(Calendar.DATE, +6);
//				String lastSunday = sdf.format(cal.getTime());
				startDate = lsatMonday;
				endDate = yesterday;
			}
			
			// 지난달 1일
			if("lastMonth".equals(dateFlag)) {
				c.add (c.MONTH, -1 ); // 이전달
				c.set(Calendar.DAY_OF_MONTH,1); // 1일
				String lastMonth1stDay = sdf.format(c.getTime());
				startDate = lastMonth1stDay;
				endDate = yesterday;
			}
			
			// 7일전
			if("sevenDays".equals(dateFlag)) {
				c.add(c.DATE, -7);
				String before7Day = sdf.format(c.getTime());
				startDate = before7Day;
				endDate = yesterday;
			}
			
			// 30일전
			if("thirtyDays".equals(dateFlag)) {
				c.add(c.MONTH, -1);
				String before30Day = sdf.format(c.getTime());
				startDate = before30Day;
				endDate = yesterday;
			}
		}else if("next".equals(flag)) {
			Calendar cal = Calendar.getInstance();
			Calendar c = Calendar.getInstance();
			try {
				cal.setTime(sdf.parse(startDate));  // startDate를 calendar로 c는 startdate
				c.setTime(sdf.parse(endDate));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			cal.add(cal.DATE, -1);
			String yesterday = sdf.format(cal.getTime());
			
			c.add(c.DATE, +1);
			String tomorrow = sdf.format(c.getTime());
			
			cal.add(cal.DATE, +1);
			c.add(c.DATE, -1);
			
			//어제
			if("yesterday".equals(dateFlag)) {
				startDate = tomorrow; 	// startDate의 어제
				endDate = tomorrow;	// startDate의 어제
			}
			
			// 이번주 월요일
			if("thisWeek".equals(dateFlag) || "lastWeek".equals(dateFlag)) {
				cal.add(cal.DATE, +7);	// startDate의 7일 전
				String thisMonday = sdf.format(cal.getTime());
				cal.add(cal.DATE, +6);
				String thisSunday = sdf.format(cal.getTime());
				startDate = thisMonday;
				endDate = thisSunday;
			}
			// 이번달 1일
			if("thisMonth".equals(dateFlag) || "lastMonth".equals(dateFlag)) {
				cal.add(cal.MONTH, +1 ); // startDate의 지난 달
				cal.set(Calendar.DAY_OF_MONTH,1); // 해당 월의 1일로 변경
				String thisMonth1stDay = sdf.format(cal.getTime());
				cal.set(Calendar.DAY_OF_MONTH,cal.getActualMaximum(Calendar.DAY_OF_MONTH)); // 해당 월의 1일로 변경
				String thisMonthLastDay = sdf.format(cal.getTime());
				startDate = thisMonth1stDay;
				endDate = thisMonthLastDay;
			}

			
			// 7일전
			if("sevenDays".equals(dateFlag)) {
				c.add(c.DATE, +7);
				String after7Day = sdf.format(c.getTime());
				startDate = tomorrow;
				endDate = after7Day;
			}
			
			// 30일전
			if("thirtyDays".equals(dateFlag)) {
				c.add(c.MONTH, +1);
				String after30Day = sdf.format(c.getTime());
				startDate = tomorrow;
				endDate = after30Day;
			}
		}

		searchParam.setStartDate(startDate);
		searchParam.setEndDate(endDate);
		
		return searchParam;
	}

}
