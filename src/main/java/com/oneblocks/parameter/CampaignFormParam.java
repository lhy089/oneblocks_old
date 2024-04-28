package com.oneblocks.parameter;

import java.util.List;

import com.oneblocks.domain.Campaign;
import com.oneblocks.domain.Product;

import lombok.Data;

@Data
public class CampaignFormParam {
	
	private String memberCampaignName;
	private Campaign campaign;
	private List<Product> optionList;
	private List<Product> supplementList;
	private List<String> productIdList;

}
