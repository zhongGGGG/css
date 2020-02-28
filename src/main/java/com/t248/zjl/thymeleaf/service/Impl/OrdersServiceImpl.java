package com.t248.zjl.thymeleaf.service.Impl;

import com.t248.zjl.thymeleaf.Respository.OrdersRepository;
import com.t248.zjl.thymeleaf.entity.Orders;
import com.t248.zjl.thymeleaf.service.OrdersService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrdersServiceImpl implements OrdersService {
    @Resource
    private OrdersRepository ordersRepository;


    @Override
    public Page<Orders> findAll( Pageable pageable) {

        Specification<Orders> ordersSpecification = new Specification<Orders>() {
            @Override
            public Predicate toPredicate(Root<Orders> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Orders> ordersList = new ArrayList<>();
                return criteriaBuilder.and(ordersList.toArray(new Predicate[ordersList.size()]));
            }
        };
        return ordersRepository.findAll(ordersSpecification, pageable);
    }

    @Override
    public Orders findByodrId(Long odrId) {
        Orders orders=ordersRepository.findByodrId(odrId);
        return orders;
    }
}
