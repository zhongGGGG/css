package com.t248.zjl.thymeleaf.Respository;

import com.t248.zjl.thymeleaf.entity.CstService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CstServiceRepository extends JpaRepository<CstService,Integer>, JpaSpecificationExecutor<CstService> {

        public Page<CstService> findAll(Specification<CstService> specification, Pageable pageable);

        public void deleteByAndSvrId(Long chcId);

        public CstService findBySvrId(Long svrId);


        /**
         * 客户服务分析 柱状图
         * @return
         */
        @Query(nativeQuery = true,value = "SELECT svr_id ,svr_type,COUNT(svr_type) cou FROM cst_service  GROUP BY svr_type")
        List<Object[]> listserce();
}
