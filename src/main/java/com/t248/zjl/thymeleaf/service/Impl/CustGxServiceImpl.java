package com.t248.zjl.thymeleaf.service.Impl;

import com.t248.zjl.thymeleaf.Respository.CustGxRespository;
import com.t248.zjl.thymeleaf.entity.CustGx;
import com.t248.zjl.thymeleaf.service.CustGxService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class CustGxServiceImpl implements CustGxService {
    @Resource
    CustGxRespository custGxRespository;

    @Override
    public List<CustGx> getCustGxAllBy(String nian, Integer currPageNo, Integer currPageSize) {
        if (currPageNo <= 0 || currPageNo == null) {
            currPageNo = 1;
        }
        if (currPageSize == null) {
            currPageSize = 2;
        }
        List<Object[]> list = custGxRespository.getGxAllByNian(nian, (currPageNo - 1) * currPageSize, currPageSize);
        List<CustGx> fxList = new ArrayList<>();
        for (Object[] obj : list) {
            CustGx custGx = new CustGx();
            custGx.setDictItem(obj[0].toString());
            custGx.setMoney(obj[1].toString());
            fxList.add(custGx);
        }
        return fxList;
    }

    @Override
    public List<CustGx> findAllsd() {
        List<Object[]> list = custGxRespository.findAllsd();
        List<CustGx> fxList = new ArrayList<>();
        for (Object[] obj : list) {
            CustGx custGx = new CustGx();
            custGx.setDictItem(obj[0].toString());
            custGx.setMoney(obj[1].toString());
            fxList.add(custGx);
        }
        return fxList;
    }
}
