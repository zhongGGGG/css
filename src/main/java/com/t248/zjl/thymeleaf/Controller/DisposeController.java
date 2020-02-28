package com.t248.zjl.thymeleaf.Controller;

import com.t248.zjl.thymeleaf.entity.CstService;
import com.t248.zjl.thymeleaf.service.CstServices;
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
public class DisposeController {
    @Resource
    CstServices cstServices;

    @RequestMapping(value = "/cstservice/dispose")
    public String list(@RequestParam(required = false) String svrType,
                       @RequestParam(required = false) String svrTitle,
                       @RequestParam(required = false) String svrCustName,

                       @RequestParam(required = false, defaultValue = "1") int pageIndex, Model model) {
        Sort sort = Sort.by(Sort.Direction.ASC, "svrId");
        Pageable pageable = PageRequest.of(pageIndex - 1, 5, sort);
        Page<CstService> cstCustomers = cstServices.findAll(svrTitle, svrType,svrCustName, pageable);

        model.addAttribute("customerPage", cstCustomers);
        model.addAttribute("svrTitle", svrTitle);
        model.addAttribute("svrType", svrType);
        model.addAttribute("svrCustName", svrCustName);

        return "/cstservice/dispose";
    }
    @RequestMapping(value = "/cstservice/update")
    public String update(Model model,Long svrId){
      CstService cstService=cstServices.findBySvrId(svrId);
      model.addAttribute("CstService",cstService);
        return "/cstservice/update";
    }
}
