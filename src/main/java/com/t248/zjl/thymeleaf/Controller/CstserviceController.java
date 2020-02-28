package com.t248.zjl.thymeleaf.Controller;

import com.sun.org.apache.xpath.internal.operations.Mod;
import com.t248.zjl.thymeleaf.entity.BasDict;
import com.t248.zjl.thymeleaf.entity.CstCustomer;
import com.t248.zjl.thymeleaf.entity.CstService;
import com.t248.zjl.thymeleaf.service.BasDictService;
import com.t248.zjl.thymeleaf.service.CstServices;
import com.t248.zjl.thymeleaf.service.CustomerService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class CstserviceController {
    @Resource
    BasDictService basDictService;
    @Resource
    CstServices cstServices;
    @Resource
    CustomerService customerService;

    @RequestMapping(value = "/cstervice/adds")
    public String adds(Model model) {
        List<CstCustomer> cstCustomers = customerService.getCstCustomers();
        model.addAttribute("cstCustomers", cstCustomers);

        List<BasDict> types = basDictService.findBasDictsByAndDictType("服务类型");
        model.addAttribute("leixing", types);
        return "cstservice/add";
    }

    @RequestMapping(value = "/cstservice/add")
    public String add(Model model, CstService cstService, String custNo) {
        CstCustomer cstCustomer = customerService.findBycustNo(custNo);
        cstService.setCstCustomer(cstCustomer);
        cstService.setSvrCustName(cstCustomer.getCustName());
        CstService cstService1 = cstServices.addCstService(cstService);

        return "redirect:/cstervice/adds";
    }

    @RequestMapping(value = "/cstservice/list")
    public String list(@RequestParam(required = false) String svrType,
                       @RequestParam(required = false) String svrTitle,
                       @RequestParam(required = false) String svrCustName,
                       @RequestParam(required = false, defaultValue = "1") int pageIndex,Model model) {
        Sort sort = Sort.by(Sort.Direction.ASC, "svrId");
        Pageable pageable = PageRequest.of(pageIndex - 1, 5, sort);
        Page<CstService> cstCustomers = cstServices.findAll(svrTitle, svrType,svrCustName, pageable);

        model.addAttribute("customerPage", cstCustomers);
        model.addAttribute("svrTitle", svrTitle);
        model.addAttribute("svrType", svrType);
        model.addAttribute("svrCustName", svrCustName);

        return "/cstservice/list";
    }


    @RequestMapping(value = "/cstservice/del")
    @ResponseBody
    @Transactional
    public Map delete(Long svrId) {
        System.out.println(svrId);
        Map map = new HashMap();
        cstServices.deleteByAndSvrId(svrId);
        map.put("delResult", "true");
        return map;
    }

    @RequestMapping(value = "/statistics/serve")
    public String listservce(Model model){
        System.out.println(123);

        List<CstService> cstServiceList=cstServices.listserce();
        System.out.println(cstServiceList);
        model.addAttribute("basDictList",cstServiceList);
        return "/statistics/serve";
    }
    @RequestMapping(value = "/statement/listserve")
    @ResponseBody
    public Map histogram(){
        Map<String,Object> map=new HashMap<>();
        List<String> list=new ArrayList<>();
        List<Integer> list1=new ArrayList<>();
        List<CstService> cstServiceList=cstServices.listserce();
        for (CstService service:cstServiceList
             ) {
            list.add(service.getSvrType() + ",");
            list1.add(Integer.parseInt(service.getCou()));
        }
        map.put("name",list);
        map.put("sum",list1);
        return map;
    }
}
