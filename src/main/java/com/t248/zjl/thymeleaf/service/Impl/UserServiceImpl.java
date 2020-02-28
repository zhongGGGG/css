package com.t248.zjl.thymeleaf.service.Impl;

import com.google.j2objc.annotations.ReflectionSupport;
import com.t248.zjl.thymeleaf.Respository.RightRepository;
import com.t248.zjl.thymeleaf.Respository.RoleRepository;
import com.t248.zjl.thymeleaf.Respository.UserRespository;
import com.t248.zjl.thymeleaf.entity.Right;
import com.t248.zjl.thymeleaf.entity.Role;
import com.t248.zjl.thymeleaf.entity.User;
import com.t248.zjl.thymeleaf.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserRespository userRespository;

    @Resource
    private RoleRepository roleRepository;

    @Resource
    private RightRepository rightRepository;

//    @Override
//    public User login(String userName, String userPassword) {
//       return null;
//    }

    @Override
    public User addUser(User user) {
        //添加
        return userRespository.save(user);
    }

    @Override
    public User deleteUser(Long id) {
        userRespository.delete(findById(id));
        return null;
    }
    @Override
    public User findByUsrId(Long userId) {
        User user=userRespository.findByUsrId(userId);
        return user;
    }

    @Override
    public User findByUsrName(String usrName, String usrPawword) {
        User user= userRespository.findByUsrName(usrName);
        if (user!=null){
            if (usrPawword.equals(user.getUsrPassword())){
                return user;
            }else{
                return null;
            }
        }else{
            return null;
        }
    }
    @Override
    public Page<User> findUsers(String usrName, Long roleId, Pageable pageable) {
        Specification<User> specification=new Specification<User>() {
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
              List<Predicate> predicates=new ArrayList<>();
              if (usrName!=null&&!usrName.equals("")){
                  predicates.add(criteriaBuilder.like(root.get("usrName"),"%"+usrName+"%"));
              }
                  Role role=new Role();
                  role.setRoleId(1L);
                  predicates.add(criteriaBuilder.notEqual(root.get("role"),role));
               return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
//        Page<User> all = userRespository.findAll(specification, pageable);
        return userRespository.findAll(specification,pageable);
    }

    @Override
    public User findById(Long id) {
        return userRespository.findById(id).get();
    }

    @Override
    public User getUser(String usrName) {
        return userRespository.findByUsrName(usrName);
    }

    @Override
    public List<Role> findAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    public Role getRoleByUser(User user) {
        return roleRepository.findRoleByUsers(user);
    }

    @Override
    public void saveRole(Role role) {
       roleRepository.save(role);
    }

    @Override
    public List<Role> findRoles(String roleName) {
        List<Role> list=null;
        if (roleName!=null){
            list=roleRepository.findRolesByRoleNameLike("%"+roleName+"%");
        }else{
            list=roleRepository.findAll();
        }
        return list;
    }

    @Override
    public List<Right> findAllRights() {

        return rightRepository.findAll();
    }

    @Override
    public List<Right> findRightsByRole(Role role) {
        return rightRepository.findRightsByRoles(role);
    }
}
