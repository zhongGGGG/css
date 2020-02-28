package com.t248.zjl.thymeleaf.Respository;


import com.t248.zjl.thymeleaf.entity.BasDict;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface BasDictRepository extends JpaRepository<BasDict, Long>, JpaSpecificationExecutor<BasDict> {
    //查询地区与客户等级
    public List<BasDict> findBasDictsByAndDictType(String dictype);

    /**
     * 分页功能
     * @param specification
     * @param pageable
     * @return
     */
    public Page<BasDict> findAll(Specification<BasDict> specification, Pageable pageable);

    public BasDict  findBydictId(Long dictId);
    public void deleteByDictId(Long dictId);
}
