package com.t248.zjl.thymeleaf.service;

import com.t248.zjl.thymeleaf.entity.OrdersLine;


public interface OrdersLineService {
    public OrdersLine findByOddId(Long oddId);
}
