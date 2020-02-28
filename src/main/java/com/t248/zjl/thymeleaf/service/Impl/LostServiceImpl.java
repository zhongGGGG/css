package com.t248.zjl.thymeleaf.service.Impl;

import com.t248.zjl.thymeleaf.Respository.LostRepository;
import com.t248.zjl.thymeleaf.entity.CstLost;
import com.t248.zjl.thymeleaf.service.LostService;
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
public class LostServiceImpl implements LostService {

    @Resource
    private LostRepository lostRepository;

    @Override
    public Page<CstLost> findAll(String lstCustName, String lstCustManagerName, Pageable pageable) {
        Specification<CstLost> specification=new Specification<CstLost>() {
            @Override
            public Predicate toPredicate(Root<CstLost> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> cstLosts=new ArrayList<>();
                if (lstCustName!=null&&!lstCustName.equals("")){
                    cstLosts.add(criteriaBuilder.like(root.get("lstCustName"),"%"+lstCustName+"%"));
                }
                if (lstCustManagerName!=null&&!lstCustManagerName.equals("")){
                    cstLosts.add(criteriaBuilder.like(root.get("lstCustManagerName"),"%"+lstCustManagerName+"%"));
                }
                return criteriaBuilder.and(cstLosts.toArray(new Predicate[cstLosts.size()]));
            }
        };
        return lostRepository.findAll(specification,pageable);
    }
}
