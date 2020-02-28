package com.t248.zjl.thymeleaf.service.Impl;


import com.t248.zjl.thymeleaf.Respository.RightRepository;
import com.t248.zjl.thymeleaf.entity.Right;
import com.t248.zjl.thymeleaf.entity.Role;
import com.t248.zjl.thymeleaf.service.RightService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RightServiceImpl implements RightService {

    @Resource
    private RightRepository rightRepository;

    @Override
    public List<Right> findAllRights() {
        return rightRepository.findAll();
    }

    @Override
    public List<Right> findRightsByRole(Role role) {
        return rightRepository.findRightsByRoles(role);
    }




}
