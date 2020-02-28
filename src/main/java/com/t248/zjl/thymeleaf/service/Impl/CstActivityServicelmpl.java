package com.t248.zjl.thymeleaf.service.Impl;

import com.t248.zjl.thymeleaf.Respository.CstActivityRepository;
import com.t248.zjl.thymeleaf.entity.CstActivity;
import com.t248.zjl.thymeleaf.service.CstActivitySercice;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class CstActivityServicelmpl implements CstActivitySercice {
    @Resource
    private CstActivityRepository cstActivityRepository;

    @Override
    public CstActivity findByatvId(Long atvId) {
        return cstActivityRepository.findByatvId(atvId);
    }

    @Override
    public CstActivity addCstActivity(CstActivity cstActivity) {
        return cstActivityRepository.save(cstActivity);
    }

    @Override
    public void delete(Long atvId) {
        cstActivityRepository.deleteById(atvId);
    }


}
