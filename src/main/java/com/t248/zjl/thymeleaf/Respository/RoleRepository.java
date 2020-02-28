package com.t248.zjl.thymeleaf.Respository;

import com.t248.zjl.thymeleaf.entity.Right;
import com.t248.zjl.thymeleaf.entity.Role;
import com.t248.zjl.thymeleaf.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role,Long>, JpaSpecificationExecutor<Role> {

   public Page<Role> findAll(Specification<Role> specification, Pageable pageable);

    public Role findByRoleId(Long roleId);

    public Role findRoleByUsers(User user);
    public List<Role> findRolesByRoleNameLike(String roleName);
}
