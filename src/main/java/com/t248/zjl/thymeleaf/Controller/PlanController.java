package com.t248.zjl.thymeleaf.Controller;

import com.t248.zjl.thymeleaf.entity.salChance;
import com.t248.zjl.thymeleaf.service.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
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
public class PlanController {

    @Autowired
    PlanService planService;
    /**
     *
     * @param chcCustName  模糊查询
     * @param chcLinkman  模糊查询
     * @param pageIndex 分页
     * @param model
     * @return
     */
    @RequestMapping(value = "/plan/list")
    public String planlist(@RequestParam(required = false) String chcCustName, @RequestParam(required = false)
            String chcLinkman,@RequestParam(required = false) String chcTitle, @RequestParam(required = false,defaultValue = "1")
                                     int pageIndex, Model model){
        Sort sort=Sort.by(Sort.Direction.ASC,"chcId");
        Pageable pageable= PageRequest.of(pageIndex-1,5,sort);
        Page<salChance> chance=planService.findSalChance(chcCustName,chcLinkman,chcTitle, pageable);

        model.addAttribute("chancePager",chance);
        model.addAttribute("chcCustName",chcCustName);
        model.addAttribute("chcLinkman",chcLinkman);
        model.addAttribute("chcTitle",chcTitle);
        return "/plan/list";
    }
}
