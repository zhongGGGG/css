package com.t248.zjl.thymeleaf.service;

import com.t248.zjl.thymeleaf.entity.CustGx;

import java.util.List;

public interface CustGxService {
    public List<CustGx> getCustGxAllBy(String nian, Integer currPageNo, Integer currPageSize);
    public List<CustGx> findAllsd();
}
