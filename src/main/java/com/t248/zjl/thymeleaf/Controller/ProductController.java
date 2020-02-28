package com.t248.zjl.thymeleaf.Controller;

import com.t248.zjl.thymeleaf.entity.Product;
import com.t248.zjl.thymeleaf.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;

@Controller
public class ProductController {

    @Resource
    ProductService productService;

    @RequestMapping(value = "/Product/list")
    public String list(Model model, @RequestParam(required = false)String prodName,
                       @RequestParam(required = false)String prodType,
                       @RequestParam(required = false)String prodBatch,
                       @RequestParam(required = false,defaultValue = "1")
                                   int pageIndex){

        Sort sort=Sort.by(Sort.Direction.ASC,"prodId");
        Pageable pageable= PageRequest.of(pageIndex-1,5,sort);
        Page<Product> products=productService.findAll(prodName,prodType,prodBatch,pageable);
        model.addAttribute("ProductPager",products);
        model.addAttribute("prodName",prodName);
        model.addAttribute("prodType",prodType);
        model.addAttribute("prodBatch",prodBatch);
        System.out.println(prodName+prodType+prodBatch);
        return "/Product/list";
    }
}
