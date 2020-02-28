package com.t248.zjl.thymeleaf.Respository;

import com.t248.zjl.thymeleaf.entity.OrdersLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


public interface OrdersLineRepository extends JpaRepository<OrdersLine, Long>, JpaSpecificationExecutor<OrdersLine> {
    public OrdersLine findByOddId(Long oddId);
}
