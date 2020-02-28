package com.t248.zjl.thymeleaf.Controller;

import com.t248.zjl.thymeleaf.entity.CstActivity;
import com.t248.zjl.thymeleaf.entity.CstCustomer;
import com.t248.zjl.thymeleaf.entity.CstLinkman;
import com.t248.zjl.thymeleaf.service.CstActivitySercice;
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
public class CstActivityController {
    @Resource
    private CstActivitySercice cstActivitySercice;
    @Resource
    CstLinkmanService cstLinkmanService;
    @Resource
    CustomerService customerService;
    @RequestMapping(value = "/cstActivity/save")
    public String save(Model model,Long custId){

        //客户名称  客户编号
        CstCustomer cstCustomer=customerService.findBycustId(custId);
        model.addAttribute("cstCustomer",cstCustomer);
        //备注
        CstLinkman cstLinkman=cstLinkmanService.findBylkmId(custId);
        model.addAttribute("cstLinkman",cstLinkman);
        System.out.println(cstLinkman.getLkmMemo());

        //交往记录
        CstActivity cstActivity=cstActivitySercice.findByatvId(custId);
        model.addAttribute("cstActivity",cstActivity);
        return "/cstActivity/save";
    }

    @RequestMapping(value = "/cstActivity/updates")
    public String updates(Model model,Long atvId){
        CstActivity cstActivity=cstActivitySercice.findByatvId(atvId);
        model.addAttribute("cstActivity",cstActivity);
        System.out.println(cstActivity.getAtvCustName());
        return "/cstActivity/upda";
    }

    @RequestMapping(value = "/cstActivity/add")
    public String cstadd(Model model,CstActivity cstActivity,String custNo){
        cstActivity.setCstCustomer(customerService.findBycustNo(custNo));
        CstActivity cstActivity1=cstActivitySercice.addCstActivity(cstActivity);
        if(cstActivity1!=null){
            return "redirect:/customer/list";
        }
       return "/cstActivity/add";
    }
    @RequestMapping(value = "/cstActivity/adds")
    public String add(Model model,Long custId){

        CstCustomer cstCustomer=customerService.findBycustId(custId);
        System.out.println(cstCustomer.getCustName());
        model.addAttribute("cstCustomer",cstCustomer);
        return "/cstActivity/add";
    }
    @RequestMapping(value = "/cstActivitu/del")
    @ResponseBody
    public Map delete(Long atvId){
        System.out.println(atvId);
        Map map= new HashMap();
        cstActivitySercice.delete(atvId);
        map.put("delResult","true");
        return map;
    }


}
