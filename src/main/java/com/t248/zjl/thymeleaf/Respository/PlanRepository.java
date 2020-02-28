package com.t248.zjl.thymeleaf.Respository;

import com.t248.zjl.thymeleaf.entity.salChance;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;



public interface PlanRepository extends JpaRepository<salChance,Integer>, JpaSpecificationExecutor<salChance> {
    /**
     * 分页查询模糊查询
     * @param specification
     * @param pageable
     * @return
     */
    public Page<salChance> findAll(Specification<salChance> specification, Pageable pageable);
}
