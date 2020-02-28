package com.t248.zjl.thymeleaf.Respository;

import com.t248.zjl.thymeleaf.entity.CstLost;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CstLostRepository extends JpaRepository<CstLost,Long> {

    /**
     * 分页 模糊查询
     * @param specification
     * @param pageable
     * @return
     */
    Page<CstLost> findAll(Specification<CstLost> specification, Pageable pageable);
    public  CstLost findBylstId(Long lstId);
}
