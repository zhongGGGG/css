package com.t248.zjl.thymeleaf.service.Impl;

import com.t248.zjl.thymeleaf.Respository.CstLinkmanRepository;
import com.t248.zjl.thymeleaf.entity.CstLinkman;
import com.t248.zjl.thymeleaf.service.CstLinkmanService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class CstLinkmanServicelmpl implements CstLinkmanService {

    @Resource
    private CstLinkmanRepository cstLinkmanRepository;

    @Override
    public CstLinkman findBylkmId(Long lkmId) {
        CstLinkman cstLinkman=cstLinkmanRepository.findBylkmId(lkmId);
        return cstLinkman;
    }

    @Override
    public void delete(Long lkmId) {
      cstLinkmanRepository.deleteById(lkmId);
    }

    @Override
    public CstLinkman addCstLinkman(CstLinkman cstLinkman) {

        return cstLinkmanRepository.save(cstLinkman);
    }
}
