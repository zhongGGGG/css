package com.t248.zjl.thymeleaf.Respository;

import com.t248.zjl.thymeleaf.entity.CustGx;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CustGxRespository extends JpaRepository<CustGx, Integer> {
    @Query(nativeQuery = true, value = "SELECT c.cust_name as dict_item,(ol.odd_count*ol.odd_price)as money FROM cst_customer as c,orders as o,orders_line as ol where c.cust_no=o.odr_customer_no and o.odr_id = ol.odd_order_id and o.odr_date LIKE CONCAT('%',:nian,'%') limit :currPageNo,:currPageSize")
    List<Object[]> getGxAllByNian(String nian, Integer currPageNo, Integer currPageSize);
    @Query(nativeQuery = true, value = "SELECT c.cust_name as dict_item,(ol.odd_count*ol.odd_price)as money FROM cst_customer as c,orders as o,orders_line as ol where c.cust_no=o.odr_customer_no and o.odr_id = ol.odd_order_id")
    List<Object[]> findAllsd();

}
