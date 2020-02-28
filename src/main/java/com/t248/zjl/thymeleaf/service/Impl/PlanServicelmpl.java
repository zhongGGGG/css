package com.t248.zjl.thymeleaf.service.Impl;

import com.t248.zjl.thymeleaf.Respository.PlanRepository;
import com.t248.zjl.thymeleaf.entity.salChance;
import com.t248.zjl.thymeleaf.service.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

@Service
public class PlanServicelmpl implements PlanService {

    @Autowired
    private PlanRepository planRepository;


    @Override
    public Page<salChance> findSalChance(String chcCustName, String chcLinkman, String chcTitle, Pageable pageable) {
        Specification<salChance> specification=new Specification<salChance>() {
            @Override
            public Predicate toPredicate(Root<salChance> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates=new ArrayList<>();
                if (chcCustName!=null&&!chcCustName.equals("")){
                    predicates.add(criteriaBuilder.like(root.get("chcCustName"),"%"+chcCustName+"%"));
                }
                if (chcLinkman!=null&&!chcLinkman.equals("")){

                    predicates.add(criteriaBuilder.like(root.get("chcLinkman"),"%"+chcLinkman+"%"));
                }

                if (chcTitle!=null&&!chcTitle.equals("")){

                    predicates.add(criteriaBuilder.like(root.get("chcTitle"),"%"+chcTitle+"%"));
                }

                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
        return planRepository.findAll(specification,pageable);
    }

    }

