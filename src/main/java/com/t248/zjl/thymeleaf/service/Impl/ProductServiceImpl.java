package com.t248.zjl.thymeleaf.service.Impl;

import com.t248.zjl.thymeleaf.Respository.ProductRepository;
import com.t248.zjl.thymeleaf.entity.Product;
import com.t248.zjl.thymeleaf.service.ProductService;
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
public class ProductServiceImpl implements ProductService {
    @Resource
    private ProductRepository productRepository;

    @Override
    public Product findByprodId(Long prodId) {
        Product product=productRepository.findByprodId(prodId);
        return product;
    }

    @Override
    public Page<Product> findAll(String prodName, String prodType, String prodBatch, Pageable pageable) {
        Specification<Product> specification=new Specification<Product>() {
            @Override
            public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> productList=new ArrayList<>();
                if (prodName!=null&&!prodName.equals("")){
                    productList.add(criteriaBuilder.like(root.get("prodName"),"%"+prodName+"%"));
                }
                if (prodType!=null&&!prodType.equals("")){
                    productList.add(criteriaBuilder.like(root.get("prodType"),"%"+prodType+"%"));
                }
                if (prodBatch!=null&&!prodBatch.equals("")){
                    productList.add(criteriaBuilder.like(root.get("prodBatch"),"%"+prodBatch+"%"));
                }

                return criteriaBuilder.and(productList.toArray(new Predicate[productList.size()]));
            }
        };
        return productRepository.findAll(specification,pageable);
    }

    @Override
    public Product findByProdName(String prodName) {
        return productRepository.findByProdName(prodName);
    }
}
