package com.t248.zjl.thymeleaf.Controller;

import com.t248.zjl.thymeleaf.entity.BasDict;
import com.t248.zjl.thymeleaf.entity.User;
import com.t248.zjl.thymeleaf.service.BasDictService;
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
public class serviceTypeController {
    @Resource
    private BasDictService basDictService;

    @RequestMapping(value = "/serviceType/list")
    public String list(Model model, @RequestParam(required = false)String dictType,
                       @RequestParam(required = false) String dictItem,
                       @RequestParam(required = false,defaultValue = "1")
                               int pageIndex){
//        List<BasDict> types = basDictService.findBasDictsByAndDictType("服务类型");
        Sort sort=Sort.by(Sort.Direction.ASC,"dictId");
        Pageable pageable= PageRequest.of(pageIndex-1,5,sort);
        Page<BasDict> basDicts=basDictService.findAll(dictItem,"服务类型",pageable);

        model.addAttribute("basDictsPager",basDicts);
        model.addAttribute("dictItem",dictItem);
        return "/serviceType/list";
    }

    @RequestMapping(value = "/serviceType/basiadds")
    public String adds(){
        return "/serviceType/basiadds";
    }

    @RequestMapping(value = "/serviceType/add_dict")
    public String add(Model model, BasDict basDict, HttpSession session){
        System.out.println(basDict.getDictValue());
        basDict.setDictType("服务类型");
        List<BasDict> types = basDictService.findBasDictsByAndDictType("服务类型");
        Integer i=types.size()+1;
        basDict.setDictValue(i.toString());
        User user = (User) session.getAttribute("user");
        basDict.setDictIsEditable(user.getUsrId());
        BasDict basDict1=basDictService.addBasDict(basDict);
        return "/serviceType/basiadds";
    }


    @RequestMapping(value = "/serviceType/update")
    public String update(Long dictId,Model model){
        BasDict basDict=basDictService.findBydictId(dictId);
        System.out.println(basDict.getDictItem());
        model.addAttribute("basDict",basDict);
        return "/serviceType/update";
    }

    @RequestMapping(value = "/serviceType/del")
    @ResponseBody
    @Transactional
    public Map delete(Long dictId){
        Map map=new HashMap();
        basDictService.deleteByDictId(dictId);
        map.put("delResult","true");
        return map;
    }
}
