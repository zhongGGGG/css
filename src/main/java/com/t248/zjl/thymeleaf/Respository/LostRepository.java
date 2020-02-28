package com.t248.zjl.thymeleaf.Respository;

import com.t248.zjl.thymeleaf.entity.CstCustomer;
import com.t248.zjl.thymeleaf.entity.CstLost;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface LostRepository extends JpaRepository<CstLost,Integer>, JpaSpecificationExecutor<CstLost> {

    /**
     * 分页
     * @param specification
     * @param pageable
     * @return
     */
    public Page<CstLost> findAll(Specification<CstLost> specification, Pageable pageable);

}
