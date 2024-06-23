package com.oneblocks.domain;

import lombok.Data;

@Data
public class ProductSales {

	private String PsalesId;
	private String productId;
	private String salesPrice;
	private int salesQuantity;
	private int revenue	;
	private int stockQuantity;
	private String updateDate;
	
	private String productName;
	private String onOffYn;
	
}
