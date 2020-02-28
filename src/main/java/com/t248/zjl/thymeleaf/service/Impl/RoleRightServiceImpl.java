package com.t248.zjl.thymeleaf.service.Impl;


import com.t248.zjl.thymeleaf.Respository.RoleRightRepository;
import com.t248.zjl.thymeleaf.entity.SysRoleRight;
import com.t248.zjl.thymeleaf.service.RoleRightService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class RoleRightServiceImpl implements RoleRightService {
    @Resource
    RoleRightRepository roleRightRepository;
    @Override
    public void save(SysRoleRight roleRight) {
       roleRightRepository.save(roleRight);
    }

    @Override
    public void delete(Long rfRoleId) {
        roleRightRepository.deleteByRfRoleId(rfRoleId);
    }
}
