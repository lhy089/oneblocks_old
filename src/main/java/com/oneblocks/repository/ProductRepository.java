package com.oneblocks.repository;

import org.springframework.stereotype.Repository;

import com.oneblocks.domain.Product;

@Repository
public interface ProductRepository {
	
	void insertProductInfo(Product product);

}
