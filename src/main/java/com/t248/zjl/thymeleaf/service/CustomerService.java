package com.t248.zjl.thymeleaf.service;

import com.t248.zjl.thymeleaf.entity.CstCustomer;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface CustomerService {

    /**
     * 分页
     * @return
     */
    public Page<CstCustomer> findAll(String custName, String custNo, String custRegion, String custManagerName, String custLevelLabel, Pageable pageable);

    //新增
    public CstCustomer addCstCustomer(CstCustomer cstCustomer);

    //修改
    public CstCustomer findBycustId(Long custId);

    //删除
    public void delete(Integer custId);

    public CstCustomer findBycustNo(String  custNo);

    public List<CstCustomer> getCstCustomers();

    public XSSFWorkbook show();

}
