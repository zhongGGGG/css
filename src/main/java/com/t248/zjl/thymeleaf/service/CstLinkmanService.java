package com.t248.zjl.thymeleaf.service;

import com.t248.zjl.thymeleaf.entity.CstLinkman;

public interface CstLinkmanService {
    //修改联系人
    public CstLinkman findBylkmId(Long lkmId);
    //删除
    public void delete(Long lkmId);
    //新增
    public CstLinkman addCstLinkman (CstLinkman cstLinkman);
}
