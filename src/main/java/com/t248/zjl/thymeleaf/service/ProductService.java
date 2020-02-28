package com.t248.zjl.thymeleaf.service;

import com.t248.zjl.thymeleaf.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {
    public Product findByprodId(Long prodId);

    public Page<Product> findAll(String prodName, String prodType, String prodBatch, Pageable pageable);
    public Product findByProdName(String prodName);
}
