package com.t248.zjl.thymeleaf.service;

import com.t248.zjl.thymeleaf.entity.Product;
import com.t248.zjl.thymeleaf.entity.Storage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface StorageService {
    public Page<Storage> findAll(Long prodId, String stkWarehouse, Pageable pageable);
}
