package com.t248.zjl.thymeleaf.service.Impl;

import com.t248.zjl.thymeleaf.Respository.BasDictRepository;
import com.t248.zjl.thymeleaf.entity.BasDict;
import com.t248.zjl.thymeleaf.service.BasDictService;
import org.springframework.beans.factory.annotation.Autowired;
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
public class BasDictServiceImpl implements BasDictService {
    @Resource
    private BasDictRepository basDictRepository;

    /**
     * 查询地区跟客户等级
     * @param dictype
     * @return
     */
    @Override
    public List<BasDict> findBasDictsByAndDictType(String dictype) {

        return basDictRepository.findBasDictsByAndDictType(dictype);
    }

    @Override
    public Page<BasDict> findAll(String dictItem,String dictType, Pageable pageable) {
        Specification<BasDict> specification=new Specification<BasDict>() {
            @Override
            public Predicate toPredicate(Root<BasDict> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
               List<Predicate> basDicts=new ArrayList<>();
               if (dictType!=null&&!dictType.equals("")){
                    basDicts.add(criteriaBuilder.like(root.get("dictType"),dictType));
                }
                if (dictItem!=null&&!dictItem.equals("")){
                    basDicts.add(criteriaBuilder.like(root.get("dictItem"),"%"+dictItem+"%"));
                }
                return criteriaBuilder.and(basDicts.toArray(new Predicate[basDicts.size()]));
            }
        };
        return basDictRepository.findAll(specification,pageable);
    }

    @Override
    public BasDict addBasDict(BasDict basDict) {
        return basDictRepository.save(basDict);
    }

    @Override
    public BasDict findBydictId(Long dictId) {
        BasDict basDict=basDictRepository.findBydictId(dictId);
        return basDict;
    }

    @Override
    public void deleteByDictId(Long dictId) {
        basDictRepository.deleteByDictId(dictId);
    }
}
