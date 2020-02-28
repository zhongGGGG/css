package com.t248.zjl.thymeleaf.service.Impl;

import com.t248.zjl.thymeleaf.Respository.CustomerRepository;
import com.t248.zjl.thymeleaf.entity.CstCustomer;
import com.t248.zjl.thymeleaf.service.CustomerService;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
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
public class CustomerServicelmpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Page<CstCustomer> findAll(String custName, String custNo, String custRegion, String custManagerName, String custLevelLabel, Pageable pageable) {
        Specification<CstCustomer> specification=new Specification<CstCustomer>() {
            @Override
            public Predicate toPredicate(Root<CstCustomer> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates=new ArrayList<>();
                if (custName!=null&&!custName.equals("")){
                    predicates.add(criteriaBuilder.like(root.get("custName"),"%"+custName+"%"));
                }
                if (custNo!=null&&!custNo.equals("")){
                    predicates.add(criteriaBuilder.like(root.get("custNo"),"%"+custNo+"%"));
                }
                if (custRegion!=null&&!custRegion.equals("0")){

                    predicates.add(criteriaBuilder.like(root.get("custRegion"),"%"+custRegion+"%"));
                }
                if (custManagerName!=null&&!custManagerName.equals("")){
                    predicates.add(criteriaBuilder.like(root.get("custManagerName"),"%"+custManagerName+"%"));
                }
                if (custLevelLabel!=null&&!custLevelLabel.equals("0")){
                    predicates.add(criteriaBuilder.like(root.get("custLevelLabel"),"%"+custLevelLabel+"%"));
                }

                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
       return customerRepository.findAll(specification,pageable);
    }

    @Override
    public CstCustomer addCstCustomer(CstCustomer cstCustomer) {
        return customerRepository.save(cstCustomer);
    }

    @Override
    public CstCustomer findBycustId(Long custId) {
        CstCustomer cstCustomer=customerRepository.findBycustId(custId);
        return cstCustomer;
    }

    @Override
    public void delete(Integer custId) {
      customerRepository.deleteById(custId);
    }

    @Override
    public CstCustomer findBycustNo(String custNo) {

        return customerRepository.findBycustNo(custNo);
    }

    @Override
    public  List<CstCustomer> getCstCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public XSSFWorkbook show() {
        List<CstCustomer> list = customerRepository.findAll();
        XSSFWorkbook wb = new XSSFWorkbook();
        Sheet sheet = wb.createSheet("Goods");//创建一张表
        Row titleRow = sheet.createRow(0);//创建第一行，起始为0
        titleRow.createCell(0).setCellValue("客户编号");//第一列
        titleRow.createCell(1).setCellValue("客户名称"); //title标题
        titleRow.createCell(2).setCellValue("客户等级");
        titleRow.createCell(3).setCellValue("客户地址");
        int cell = 1;
        for (CstCustomer cst : list) {
            //注意控制行
            Row row = sheet.createRow(cell);//从第二行开始保存数据
            row.createCell(0).setCellValue(cst.getCustNo());
            row.createCell(1).setCellValue(cst.getCustName());//将数据库的数据遍历出来
            row.createCell(2).setCellValue(cst.getCustLevelLabel());
            row.createCell(3).setCellValue(cst.getCustAddr());
            cell++;
        }
        return wb;
    }
}
