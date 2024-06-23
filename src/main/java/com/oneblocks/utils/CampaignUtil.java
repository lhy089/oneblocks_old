package com.oneblocks.utils;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.oneblocks.domain.Campaign;
import com.oneblocks.domain.Product;

public class CampaignUtil {
	
	public static JSONObject getCampaignDataByCampaignUrl(String campaignUrl) {
		JSONObject result = null;
		try {
			 String url = URLDecoder.decode(campaignUrl, "UTF-8");
			 Document doc = Jsoup.connect(url).get();

			 Elements scriptTags = doc.select("script"); 
			 String script = "";
			 for(int i=0; i<scriptTags.size(); i++) {
				 script = scriptTags.get(i).toString();
				 if(script.contains("window.__PRELOADED_STATE__")) {
					 script = script.replace("<script>window.__PRELOADED_STATE__=", "").replace("</script>","");

					 break;
				 }
			 }
			 try {
				 JSONObject jsonObj=new JSONObject(script);
				 JSONObject product = (JSONObject) jsonObj.get("product");
				 String id = (String)((JSONObject) product.get("A")).get("id");
				 result = (JSONObject) product.get("A");
			 }catch(Exception e) {
				 e.printStackTrace();
			 }
		 } catch (IOException e) {
			 // TODO Auto-generated catch block
			 e.printStackTrace();
		 }
		return result;
	}
	
	public static Campaign getCampaignInfo(JSONObject campaignData) {
		Campaign campaign = new Campaign();
		campaign.setCampaignId(String.valueOf(campaignData.get("id")));
		campaign.setCampaignName((String) campaignData.get("name"));
		return campaign;
	}
	
	public static List<Product> getOptionList(JSONObject campaignData) {
		List<Product> productList = new ArrayList<Product>();
		Boolean optionUsable = (Boolean) campaignData.get("optionUsable");
		 
		if(optionUsable) {
			JSONArray options = (JSONArray) campaignData.get("options");
			JSONArray productData = (JSONArray) campaignData.get("optionCombinations");
	 
			for(int i=0; i<productData.length(); i++){ 
				Product product = new Product();
				JSONObject item = (JSONObject) productData.get(i);
				product.setProductId(String.valueOf(item.get("id")));
				
				String name = (String) item.get("optionName1");
				if(options.length()==2) name = name + " / " + (String) item.get("optionName2");
				if(options.length()==3) name = name + " / " + (String) item.get("optionName2") +" / " + (String) item.get("optionName3");
				
				
				product.setProductName((String) campaignData.get("name")+"(" + name + ")");
				product.setProductRealName(name);
				product.setProductPrice(String.valueOf((Integer) campaignData.get("discountedSalePrice") + (Integer) item.get("price")));
				productList.add(product);
			}
		}
			
		return productList;
	}
	
	public static List<Product> getSupplementList(JSONObject campaignData) {	
		Boolean supplementProductUsable = (Boolean) campaignData.get("supplementProductUsable");
		List<Product> productList = new ArrayList<Product>();
		
		if(supplementProductUsable) {
			JSONArray productData = (JSONArray) campaignData.get("supplementProducts");
			for(int i=0; i<productData.length(); i++){ 
				Product product = new Product();
				JSONObject item = (JSONObject) productData.get(i);
				product.setProductId(String.valueOf(item.get("id")));
				product.setProductName((String) campaignData.get("name")+"(" + (String) item.get("name") + ")");
				product.setProductRealName((String) item.get("name"));
				product.setProductPrice(String.valueOf((Integer) item.get("price")));
				product.setGroupName((String) item.get("groupName"));
				productList.add(product);
			}
		}
		return productList;
	}

}
