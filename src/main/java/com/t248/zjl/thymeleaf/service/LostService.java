package com.t248.zjl.thymeleaf.service;

import com.t248.zjl.thymeleaf.entity.CstLost;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface LostService {

    public Page<CstLost> findAll(String lstCustName,String lstCustManagerName, Pageable pageable);
}
