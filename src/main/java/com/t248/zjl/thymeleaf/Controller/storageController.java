package com.t248.zjl.thymeleaf.Controller;

import com.t248.zjl.thymeleaf.entity.Product;
import com.t248.zjl.thymeleaf.entity.Storage;
import com.t248.zjl.thymeleaf.service.ProductService;
import com.t248.zjl.thymeleaf.service.StorageService;
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
public class storageController {

    @Resource
    private StorageService storageService;
    @Resource
    ProductService productService;

    @RequestMapping(value = "/storage/list")
    public String list(Model model, @RequestParam(required = false)String  prodName,
                       @RequestParam(required = false)String stkWarehouse,
                          @RequestParam(required = false,defaultValue = "1") int pageIndex){
        Sort sort=Sort.by(Sort.Direction.ASC,"stkId");
        Pageable pageable= PageRequest.of(pageIndex-1,5,sort);
        Page<Storage> storages=null;
        if (prodName==null||prodName==""){
            storages=storageService.findAll(null,stkWarehouse,pageable);
        }else{
            Product product=productService.findByProdName(prodName);
            storages=storageService.findAll(product.getProdId(),stkWarehouse,pageable);
        }
        model.addAttribute("StoragePager",storages);
        model.addAttribute("prodName",prodName);
        model.addAttribute("stkWarehouse",stkWarehouse);
        return "/storage/list";
    }
}
