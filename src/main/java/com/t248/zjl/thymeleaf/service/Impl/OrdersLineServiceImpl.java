package com.t248.zjl.thymeleaf.service.Impl;

import com.t248.zjl.thymeleaf.Respository.OrdersLineRepository;
import com.t248.zjl.thymeleaf.entity.Orders;
import com.t248.zjl.thymeleaf.entity.OrdersLine;
import com.t248.zjl.thymeleaf.service.OrdersLineService;
import com.t248.zjl.thymeleaf.service.OrdersService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class OrdersLineServiceImpl implements OrdersLineService {
    @Resource
    private OrdersLineRepository ordersLineRepository;

    @Override
    public OrdersLine findByOddId(Long oddId) {
        OrdersLine ordersLine=ordersLineRepository.findByOddId(oddId);
        return ordersLine;
    }
}
