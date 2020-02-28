package com.t248.zjl.thymeleaf.service.Impl;

import com.t248.zjl.thymeleaf.Respository.CstServiceRepository;
import com.t248.zjl.thymeleaf.entity.CstService;
import com.t248.zjl.thymeleaf.service.CstServices;
import com.t248.zjl.thymeleaf.service.CustomerService;
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
public class CstSerciceImpl implements CstServices {
    @Resource
    private CstServiceRepository cstServiceRepository;

    @Override
    public CstService addCstService(CstService cstService) {

        return cstServiceRepository.save(cstService);
    }

    @Override
    public Page<CstService> findAll(String svrTitle, String svrType, String svrCustName, Pageable pageable) {
        Specification<CstService> specification=new Specification<CstService>() {

            @Override
            public Predicate toPredicate(Root<CstService> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates=new ArrayList<>();
                if (svrTitle!=null&&!svrTitle.equals("")){
                    predicates.add(criteriaBuilder.like(root.get("svrTitle"),"%"+svrTitle+"%"));
                }
                if (svrType!=null&&!svrType.equals("0")){
                    predicates.add(criteriaBuilder.like(root.get("svrType"),"%"+svrType+"%"));
                }
                if (svrType!=null&&!svrType.equals("")){
                    predicates.add(criteriaBuilder.like(root.get("svrCustName"),"%"+svrCustName+"%"));
                }
//                if (svrStatus!=null&&!svrStatus.equals("")){
//                    predicates.add(criteriaBuilder.like(root.get("已归档"),"%"+svrStatus+"%"));
//                }

                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
        return cstServiceRepository.findAll(specification,pageable);
    }

    @Override
    public void deleteByAndSvrId(Long svrId) {
         cstServiceRepository.deleteByAndSvrId(svrId);
    }

    @Override
    public CstService findBySvrId(Long svrId) {
        CstService cstService=cstServiceRepository.findBySvrId(svrId);
        return cstService;
    }

    @Override
    public List<CstService> listserce() {
        List<Object[]> list=cstServiceRepository.listserce();
        List<CstService> list1=new ArrayList<>();
        for (Object[] obj: list
             ) {
            CstService cstService=new CstService();
            cstService.setSvrId((long) Integer.parseInt(obj[0].toString()));
            cstService.setSvrType(obj[1].toString());
            cstService.setCou(obj[2].toString());
             list1.add(cstService);
        }
        return list1;
    }


}
