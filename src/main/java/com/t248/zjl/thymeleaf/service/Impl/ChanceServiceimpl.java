package com.t248.zjl.thymeleaf.service.Impl;

import com.t248.zjl.thymeleaf.Respository.ChanceRepository;
import com.t248.zjl.thymeleaf.entity.salChance;
import com.t248.zjl.thymeleaf.service.ChanceService;
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
import java.util.Optional;

@Service
public class ChanceServiceimpl implements ChanceService {

    @Resource
    private ChanceRepository chanceRepository;

    @Override
    public Page<salChance> findSalChance(String chcCustName,String chcDesc, Pageable pageable) {
        Specification<salChance> specification=new Specification<salChance>() {
            @Override
            public Predicate toPredicate(Root<salChance> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates=new ArrayList<>();
                if (chcCustName!=null&&!chcCustName.equals("")){
                    predicates.add(criteriaBuilder.like(root.get("chcCustName"),"%"+chcCustName+"%"));
                }
                if (chcDesc!=null&&!chcDesc.equals("")){

                    predicates.add(criteriaBuilder.like(root.get("chcDesc"),"%"+chcDesc+"%"));
                }

                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
        return chanceRepository.findAll(specification,pageable);
    }

    @Override
    public salChance addSalChance(salChance chance) {
        //新增
        return chanceRepository.save(chance);
    }

    @Override
    public salChance findByChcId(Long chcId) {
        //修改
        salChance chance=chanceRepository.findByChcId(chcId);
        return chance;
    }

    @Override
    public void deleteByAndChcId(Long chcId) {
        chanceRepository.deleteByAndChcId(chcId);
    }


}
