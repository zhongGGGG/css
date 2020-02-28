package com.t248.zjl.thymeleaf.service;


import com.t248.zjl.thymeleaf.entity.salChance;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;

public interface PlanService {

    /**
     * 分页查询跟模糊查询
     * @param chcCustName
     * @param chcLinkman
     * @param chcTitle
     * @param pageable
     * @return
     */
    public Page<salChance> findSalChance(String chcCustName, String chcLinkman, String chcTitle, Pageable pageable);
}
