package com.t248.zjl.thymeleaf.Respository;

import com.t248.zjl.thymeleaf.entity.Right;
import com.t248.zjl.thymeleaf.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RightRepository extends JpaRepository<Right,String> {
    public List<Right> findRightsByRoles(Role role);
}
