package com.t248.zjl.thymeleaf.Respository;

import com.t248.zjl.thymeleaf.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;



public interface ProductRepository extends JpaRepository<Product,Long>, JpaSpecificationExecutor<Product> {
    public Product findByprodId(Long prodId);

    public Page<Product> findAll(Specification<Product> specification, Pageable pageable);

    public Product findByProdName(String prodName);
}
