package com.t248.zjl.thymeleaf.Respository;

import com.t248.zjl.thymeleaf.entity.CstActivity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CstActivityRepository extends JpaRepository<CstActivity,Long> {

    public CstActivity findByatvId(Long atvId);
}
