package com.t248.zjl.thymeleaf.Controller;

import com.t248.zjl.thymeleaf.entity.Constitute;
import com.t248.zjl.thymeleaf.service.ConstService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ConstController {

    @Resource
    private ConstService constituteService;

    @RequestMapping(value = "/statistics/constitute")
    public String list(Model model){
        List<Constitute> constitutes=constituteService.listConstitute();

        model.addAttribute("basDictList",constitutes);

        return "/statistics/constitute";
    }

    @RequestMapping(value = "/statement/con")
    @ResponseBody
    public Map con() {
        Map<String, Object> map = new HashMap<>();
        List<String> list = new ArrayList<>();
        List<Integer> list1 = new ArrayList<>();
        List<Constitute> custGxes = constituteService.listquanbu();
        for (Constitute constitute : custGxes) {
            list.add(constitute.getValu() + ",");
            list1.add(Integer.parseInt(constitute.getItems()));
        }
        map.put("name", list);
        map.put("sum",list1);
        return map;
    }
}
