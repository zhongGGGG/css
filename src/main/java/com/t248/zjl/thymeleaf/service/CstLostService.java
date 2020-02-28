package com.t248.zjl.thymeleaf.service;

import com.t248.zjl.thymeleaf.entity.CstLost;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CstLostService {
    Page<CstLost> findAll(String lstCustName, String lstCustManagerName, String lstStatus, Pageable pageable);
    public  CstLost findBylstId(Long lstId);
    public  void save(CstLost cstLost);
}
