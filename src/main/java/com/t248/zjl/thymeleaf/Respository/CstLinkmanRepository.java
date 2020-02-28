package com.t248.zjl.thymeleaf.Respository;

import com.t248.zjl.thymeleaf.entity.CstLinkman;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CstLinkmanRepository extends JpaRepository<CstLinkman,Long> {
    public CstLinkman findBylkmId(Long lkmId);
}
