package com.t248.zjl.thymeleaf.Controller;

import com.t248.zjl.thymeleaf.entity.CstLost;
import com.t248.zjl.thymeleaf.service.LostService;
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
public class LostController {
    @Resource
    private LostService lostService;


    @RequestMapping(value = "/statistics/list")
    public String list(Model model, @RequestParam(required = false) String lstCustName,
                       @RequestParam(required = false) String lstCustManagerName,
                       @RequestParam(required = false, defaultValue = "1")int pageIndex){
        Sort sort=Sort.by(Sort.Direction.ASC,"lstId");
        Pageable pageable= PageRequest.of(pageIndex-1,5,sort);
        Page<CstLost> cstLosts=lostService.findAll(lstCustName,lstCustManagerName,pageable);
        model.addAttribute("cstLosts",cstLosts);
        model.addAttribute("lstCustName",lstCustName);
        model.addAttribute("lstCustManagerName",lstCustManagerName);
        return "/statistics/list";
    }
}
