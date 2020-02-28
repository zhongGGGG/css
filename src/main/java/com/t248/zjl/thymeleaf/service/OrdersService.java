package com.t248.zjl.thymeleaf.service;

import com.t248.zjl.thymeleaf.entity.Orders;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

public interface OrdersService {
    public Page<Orders> findAll( Pageable pageable);

    public Orders findByodrId(Long odrId);
}
