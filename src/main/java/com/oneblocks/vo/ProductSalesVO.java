package com.oneblocks.vo;

import lombok.Data;

@Data
public class ProductSalesVO {
	
	private String salesId;
	private String productId;
	private String productPrice;
	private String productName;
	private String onOffYn;
	private String totalSalesQuantity;
	private String totalSalesRevenue;
	private String updateDate;
}
