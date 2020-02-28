package com.t248.zjl.thymeleaf.service;

import com.t248.zjl.thymeleaf.entity.CstActivity;

public interface CstActivitySercice {
    public CstActivity findByatvId(Long atvId);
    public  CstActivity addCstActivity(CstActivity cstActivity);
    public void delete(Long atvId);
}
