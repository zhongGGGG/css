package com.t248.zjl.thymeleaf.service.Impl;

import com.t248.zjl.thymeleaf.Respository.StorageRepository;
import com.t248.zjl.thymeleaf.entity.Product;
import com.t248.zjl.thymeleaf.entity.Storage;
import com.t248.zjl.thymeleaf.service.StorageService;
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
public class StorageServiceImpl implements StorageService {
    @Resource
    private StorageRepository storageRepository;


    @Override
    public Page<Storage> findAll(Long prodId, String stkWarehouse, Pageable pageable) {
        Specification<Storage> specification=new Specification<Storage>() {
            @Override
            public Predicate toPredicate(Root<Storage> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> productList=new ArrayList<>();
                if (prodId!=null&&prodId!=0){
                    Product product=new Product();
                    product.setProdId(prodId);
                    productList.add(criteriaBuilder.equal(root.get("product"),product));
                }
                if (stkWarehouse!=null&&!stkWarehouse.equals("")){
                    productList.add(criteriaBuilder.like(root.get("stkWarehouse"),"%"+stkWarehouse+"%"));
                }

                return criteriaBuilder.and(productList.toArray(new Predicate[productList.size()]));
            }
        };
        return storageRepository.findAll(specification,pageable);
        }
}
