package com.t248.zjl.thymeleaf.Controller;

import com.t248.zjl.thymeleaf.entity.CstLost;
import com.t248.zjl.thymeleaf.service.CstLostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CstLostController  {
    @Autowired
    private CstLostService cstLostService;

    @RequestMapping(value = "/cstLost/list")
    public String list(Model model, @RequestParam(required = false)String lstCustName,
                       @RequestParam(required = false) String lstCustManagerName,
                       @RequestParam(required = false) String lstStatus,
                       @RequestParam(required = false,defaultValue = "1") int pageIndex){
        Sort sort = Sort.by(Sort.Direction.ASC, "lstId");
        //控制分页的辅助类，可以设置页码(从0开始！！！)、每页的数据条数、排序等
        Pageable pageable = PageRequest.of(pageIndex-1, 5, sort);
        Page<CstLost> cstLosts = cstLostService.findAll(lstCustName,lstCustManagerName,lstStatus,pageable);
        model.addAttribute("lstCustName",lstCustName);
        model.addAttribute("lstCustManagerName",lstCustManagerName);
        model.addAttribute("lstStatus",lstStatus);
        model.addAttribute("cstLosts",cstLosts);
        return "/cstLost/list";
    }

    @RequestMapping(value = "cstLost/DeterminemeLost")
    public String findById(Long lstId,Model model){
        CstLost cstLost=cstLostService.findBylstId(lstId);
        model.addAttribute("cstLost",cstLost);
        return "/cstLost/DetermineLost";
    }
    @RequestMapping(value = "cstLost/yesLost")
    public String findByIdyes(Long lstId,Model model){
        CstLost cstLost=cstLostService.findBylstId(lstId);
        model.addAttribute("cstLost",cstLost);
        return "/cstLost/yesLost";
    }
    @RequestMapping(value = "/DetermineLost/save")
    public String save(CstLost cstLost,String lstReasons,Model model){
        System.out.println(lstReasons);
        String lstReason=cstLost.getLstReason();
        StringBuffer stringBuffer=new StringBuffer();
        stringBuffer.append(cstLost.getLstReason());
        stringBuffer.append(lstReasons);
        cstLost.setLstReason(stringBuffer.toString());
        cstLostService.save(cstLost);
        return "redirect:/cstLost/list";
    }
    @RequestMapping(value = "/yesLost/save")
    public String save(CstLost cstLost,Model model){
        cstLost.setLstStatus("确定流失");
        String lstReason=cstLost.getLstReason();
        cstLostService.save(cstLost);
        return "redirect:/cstLost/list";
    }
}
