package com.t248.zjl.thymeleaf.Respository;

import com.t248.zjl.thymeleaf.entity.Storage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface StorageRepository  extends JpaRepository<Storage,Long>, JpaSpecificationExecutor<Storage> {
    public Page<Storage> findAll(Specification<Storage> specification, Pageable pageable);
}
