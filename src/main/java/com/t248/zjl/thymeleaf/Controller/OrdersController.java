package com.t248.zjl.thymeleaf.Controller;

import com.t248.zjl.thymeleaf.Respository.OrdersRepository;
import com.t248.zjl.thymeleaf.entity.*;
import com.t248.zjl.thymeleaf.service.*;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class OrdersController {
    @Resource
    OrdersService ordersService;

    @Resource
    CustomerService customerService;

    @Resource
    ProductService productService;

    @Resource
    OrdersLineService ordersLineService;


    @Resource
    CustGxService custGxService;

    @RequestMapping(value = "/orders/save")
    public String list(Model model ,@RequestParam(required = false,defaultValue = "1")int pageIndex,Long custId){
        //客户名称  客户编号
        System.out.println(custId+"打印");
        CstCustomer cstCustomer=customerService.findBycustId(custId);
        System.out.println(cstCustomer.getCustNo()+cstCustomer.getCustName()+"进入");
        model.addAttribute("cstCustomer",cstCustomer);

        Sort sort=Sort.by(Sort.Direction.ASC,"odrId");
        Pageable pageable= PageRequest.of(pageIndex-1,5,sort);
        Page<Orders> orders=ordersService.findAll(pageable);
        model.addAttribute("ordersStatPage",orders);
        return "/orders/list";
    }
    @RequestMapping(value = "/ordersStat/edit")
    public String edit(Model model,Long odrId){
        Orders orders=ordersService.findByodrId(odrId);
        model.addAttribute("orders",orders);

        Product products=productService.findByprodId(orders.getOdrId());
        System.out.println(products.getProdUnit()+products.getProdName()+"3962510");
        model.addAttribute("products",products);

       OrdersLine ordersLine=ordersLineService.findByOddId(orders.getOdrId());
        model.addAttribute("ordersLine",ordersLine);
        System.out.println(ordersLine.getOddCount()+"数量");
        return "/orders/edit";
    }
}
