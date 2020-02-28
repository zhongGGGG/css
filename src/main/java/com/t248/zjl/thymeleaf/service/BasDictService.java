package com.t248.zjl.thymeleaf.service;

import com.t248.zjl.thymeleaf.entity.BasDict;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BasDictService {
    /**
     * 查询地区跟客户等级
     * @param dictype
     * @return
     */
    public List<BasDict> findBasDictsByAndDictType(String dictype);

    public Page<BasDict> findAll(String dictItem,String dictType, Pageable pageable);

    public BasDict addBasDict(BasDict basDict);
    public BasDict  findBydictId(Long dictId);
    public void deleteByDictId(Long dictId);
}
