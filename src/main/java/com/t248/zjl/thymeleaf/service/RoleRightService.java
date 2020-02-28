package com.t248.zjl.thymeleaf.service;

import com.t248.zjl.thymeleaf.entity.SysRoleRight;

public interface RoleRightService {

    public void save(SysRoleRight roleRight);

    public void delete(Long rfRoleId);
}
