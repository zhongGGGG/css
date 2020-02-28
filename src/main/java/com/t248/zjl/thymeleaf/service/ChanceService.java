package com.t248.zjl.thymeleaf.service;

import com.t248.zjl.thymeleaf.entity.salChance;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface ChanceService {

    /**
     * 分页查询分页
     * @param chcCustName
     * @param chcDesc
     * @param pageable
     * @return
     */
    public Page<salChance> findSalChance(String chcCustName, String chcDesc, Pageable pageable);

    //新增
    public salChance addSalChance(salChance chance);

    //修改
    public salChance findByChcId(Long chcId);

    //删除
    public void deleteByAndChcId(Long chcId);
}
