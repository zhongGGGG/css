package com.t248.zjl.thymeleaf.service;


import com.t248.zjl.thymeleaf.entity.Right;
import com.t248.zjl.thymeleaf.entity.Role;

import java.util.List;

public interface RightService {

    public List<Right> findAllRights();

    public List<Right> findRightsByRole(Role role);

}
