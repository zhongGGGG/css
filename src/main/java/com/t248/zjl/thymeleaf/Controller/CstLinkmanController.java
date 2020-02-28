package com.t248.zjl.thymeleaf.Controller;

import com.t248.zjl.thymeleaf.entity.CstCustomer;
import com.t248.zjl.thymeleaf.entity.CstLinkman;
import com.t248.zjl.thymeleaf.service.CstLinkmanService;
import com.t248.zjl.thymeleaf.service.CustomerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Controller
public class CstLinkmanController {
    @Resource
    CstLinkmanService cstLinkmanService;
    @Resource
    CustomerService customerService;
    @RequestMapping(value = "/customer/compile")
    public String edits(Model model, Long custId){
        CstLinkman cstLinkman=cstLinkmanService.findBylkmId(custId);
        model.addAttribute("cstLinkman",cstLinkman);

        return "/customer/compile";
    }

    @RequestMapping(value = "/customer/compileAdd")
    public String add(Model model,CstLinkman cstLinkman,String custNo){
        cstLinkman.setCstCustomer(customerService.findBycustNo(custNo));
        CstLinkman cstLinkman1=cstLinkmanService.addCstLinkman(cstLinkman);
        if (cstLinkman1!=null){
            return "redirect:/customer/list";
        }
        return "/customer/compileAdd";
    }
    @RequestMapping(value = "/customer/add")
    public String addcomp(Long custId,Model model){
        CstCustomer cstCustomer=customerService.findBycustId(custId);
        model.addAttribute("cstCustomer",cstCustomer);
        return "/customer/compileAdd";
    }

    @RequestMapping(value = "/customer/del")
    @ResponseBody
    public Map delete(Long custId){
        System.out.println(custId);
        Map map= new HashMap();
        cstLinkmanService.delete(custId);
        map.put("delResult","true");
        return map;
    }


}
