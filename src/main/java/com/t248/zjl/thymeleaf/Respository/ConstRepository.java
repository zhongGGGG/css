package com.t248.zjl.thymeleaf.Respository;

import com.t248.zjl.thymeleaf.entity.Constitute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ConstRepository extends JpaRepository<Constitute,String> {

    @Query(nativeQuery = true, value = "SELECT d.`dict_value`,d.`dict_item`,COUNT(c.`cust_level`) COUNT\n" +
            "FROM `bas_dict` d LEFT JOIN `cst_customer` c ON d.`dict_value`=c.`cust_level`\n" +
            "WHERE d.`dict_type`='客户等级'\n" +
            "GROUP BY d.`dict_value`\n")
    List<Object[]> listConstitute();

    @Query(nativeQuery = true, value = "SELECT d.`dict_value`,d.`dict_item`,COUNT(c.`cust_level`) COUNT\n" +
            "FROM `bas_dict` d LEFT JOIN `cst_customer` c ON d.`dict_value`=c.`cust_level`\n" +
            "WHERE d.`dict_type`='客户等级'\n" +
            "GROUP BY d.`dict_value`\n")
    List<Object[]> listquanbu();


}
