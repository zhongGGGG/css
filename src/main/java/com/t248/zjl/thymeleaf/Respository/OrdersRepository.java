package com.t248.zjl.thymeleaf.Respository;


import com.t248.zjl.thymeleaf.entity.Orders;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface OrdersRepository extends JpaRepository<Orders,Long>, JpaSpecificationExecutor<Orders> {

    /**
     * 分页
     * @param specification
     * @param pageable
     * @return
     */
    public Page<Orders> findAll(Specification<Orders> specification, Pageable pageable);
    public Orders findByodrId(Long odrId);
}
