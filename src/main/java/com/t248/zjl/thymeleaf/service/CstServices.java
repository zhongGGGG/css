package com.t248.zjl.thymeleaf.service;

import com.t248.zjl.thymeleaf.entity.CstService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CstServices {

   public CstService addCstService(CstService cstService);

   public Page<CstService> findAll(String svrTitle,String svrType,String svrCustName, Pageable pageable);



   public void deleteByAndSvrId(Long svrId);

   public CstService findBySvrId(Long svrId);

   List<CstService> listserce();

}
