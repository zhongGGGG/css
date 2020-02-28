package com.t248.zjl.thymeleaf.Respository;

import com.t248.zjl.thymeleaf.entity.Right;
import com.t248.zjl.thymeleaf.entity.Role;
import com.t248.zjl.thymeleaf.entity.SysRoleRight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;


public interface RoleRightRepository extends JpaRepository<SysRoleRight,Long>, JpaSpecificationExecutor<SysRoleRight> {
    @Transactional
    @Query(value = " DELETE FROM sys_role_right WHERE rf_role_id=?1 ",nativeQuery = true)
    @Modifying
    public  void deleteByRfRoleId(Long rfRoleId);

}
