package com.t248.zjl.thymeleaf.Controller;

import com.sun.org.apache.xpath.internal.operations.Mod;
import com.t248.zjl.thymeleaf.entity.BasDict;
import com.t248.zjl.thymeleaf.entity.User;
import com.t248.zjl.thymeleaf.entity.salChance;
import com.t248.zjl.thymeleaf.service.BasDictService;
import org.hibernate.validator.constraints.pl.REGON;
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
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class BasDictController {
    @Resource
    private BasDictService basDictService;

    @RequestMapping(value = "/basics/list")
    public String list(Model model, @RequestParam(required = false)String dictType,
                       @RequestParam(required = false) String dictItem,
                       @RequestParam(required = false,defaultValue = "1")
                                   int pageIndex){
//        List<BasDict> types = basDictService.findBasDictsByAndDictType("客户等级");
        Sort sort=Sort.by(Sort.Direction.ASC,"dictId");
        Pageable pageable= PageRequest.of(pageIndex-1,5,sort);
        Page<BasDict> basDicts=basDictService.findAll(dictItem,"客户等级",pageable);

        model.addAttribute("basDictsPager",basDicts);
        model.addAttribute("dictItem",dictItem);
        return "/basics/list";
    }

    @RequestMapping(value = "/basics/basiadd")
    public String adds(){
        return "/basics/basiadd";
    }

    @RequestMapping(value = "/basics/add_dict")
    public String add(Model model, BasDict basDict, HttpSession session){
        System.out.println(basDict.getDictValue());
        basDict.setDictType("客户等级");
        List<BasDict> types = basDictService.findBasDictsByAndDictType("客户等级");
        Integer i=types.size()+1;
        basDict.setDictValue(i.toString());
        User user = (User) session.getAttribute("user");
        basDict.setDictIsEditable(user.getUsrId());
       BasDict basDict1=basDictService.addBasDict(basDict);
        return "/basics/basiadd";
    }


    @RequestMapping(value = "/basics/update")
    public String update(Long dictId,Model model){
        BasDict basDict=basDictService.findBydictId(dictId);
        System.out.println(basDict.getDictItem());
        model.addAttribute("basDict",basDict);
        return "/basics/update";
    }

    @RequestMapping(value = "/basics/del")
    @ResponseBody
    @Transactional
    public Map delete(Long dictId){
        Map map=new HashMap();
        basDictService.deleteByDictId(dictId);
        map.put("delResult","true");
        return map;
    }
}
