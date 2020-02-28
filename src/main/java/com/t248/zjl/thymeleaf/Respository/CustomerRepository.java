package com.t248.zjl.thymeleaf.Respository;

import com.t248.zjl.thymeleaf.entity.CstCustomer;
import com.t248.zjl.thymeleaf.entity.CstLinkman;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;


public interface CustomerRepository extends JpaRepository<CstCustomer,Integer>, JpaSpecificationExecutor<CstCustomer> {

    /**
     * 分页
     * @param specification
     * @param pageable
     * @return
     */
    public Page<CstCustomer> findAll(Specification<CstCustomer> specification, Pageable pageable);

    public CstCustomer findBycustId(Long custId);
    public CstCustomer findBycustNo(String custNo);



}
