package com.t248.zjl.thymeleaf.service;

import com.t248.zjl.thymeleaf.entity.Right;
import com.t248.zjl.thymeleaf.entity.Role;
import com.t248.zjl.thymeleaf.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {
//    public User login(String userName,String userPassword);
    public User addUser(User user);
    public User deleteUser(Long user);
    public User findByUsrId(Long userId);
//    public List<User> findAll();
    public User findByUsrName(String usrName,String usrPawword);
    public Page<User> findUsers(String usrName, Long roleId, Pageable pageable);
    public User findById(Long id);

    public User getUser(String usrName);

    //获取所有角色
    public List<Role> findAllRoles();
    //获取用户-->角色
    public Role getRoleByUser(User user);
    //保存角色
    public void saveRole(Role role);
    //获取角色
    public List<Role> findRoles(String roleName);
    //获取所有权限呢
    public List<Right> findAllRights();
    //获取用户-->角色-->权限
    public List<Right> findRightsByRole(Role role);
}
