package com.oneblocks.utils;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.oneblocks.vo.ProductSalesVO;

public class ExcelUtil {

	public static Workbook excelDownloadForProductList(List<ProductSalesVO> salesList) throws IOException {
		Workbook workbook = new HSSFWorkbook();
		Sheet sheet = workbook.createSheet("프로덕트");
		int rowNo = 0;


		Row headerRow = sheet.createRow(rowNo++);
		headerRow.createCell(0).setCellValue("옵션명");
		headerRow.createCell(1).setCellValue("판매가");
		headerRow.createCell(2).setCellValue("판매수량");
		headerRow.createCell(3).setCellValue("매출액");
		headerRow.createCell(4).setCellValue("업데이트");
		headerRow.createCell(5).setCellValue("On/Off");

		DecimalFormat df = new DecimalFormat("###,###");
		for (ProductSalesVO data : salesList) {
			Row row = sheet.createRow(rowNo++);
			row.createCell(0).setCellValue(data.getProductName());
			row.createCell(1).setCellValue(data.getProductPrice());
			row.createCell(2).setCellValue(data.getTotalSalesQuantity());
			row.createCell(3).setCellValue(data.getTotalSalesRevenue());
			row.createCell(4).setCellValue(data.getUpdateDate());
			row.createCell(5).setCellValue("Y".equals(data.getOnOffYn()) ? "ON" : "OFF");
		}

		sheet.setColumnWidth(0, 10000);
		sheet.setColumnWidth(1, 3000);
		sheet.setColumnWidth(2, 3000);
		sheet.setColumnWidth(3, 3000);
		sheet.setColumnWidth(4, 5000);
		sheet.setColumnWidth(5, 2000);

		return workbook;
	}
}
