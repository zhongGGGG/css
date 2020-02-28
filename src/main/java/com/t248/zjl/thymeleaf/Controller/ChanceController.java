package com.t248.zjl.thymeleaf.Controller;

import com.t248.zjl.thymeleaf.entity.salChance;
import com.t248.zjl.thymeleaf.service.ChanceService;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

@Controller
public class ChanceController {
    @Resource
    ChanceService chanceService;

    /**
     *
     * @param chcCustName  模糊查询
     * @param chcDesc  模糊查询
     * @param pageIndex 分页
     * @param model
     * @return
     */
    @RequestMapping(value = "/chance/list")
    public String chanselist(@RequestParam(required = false) String chcCustName,
                             @RequestParam(required = false) String chcDesc,
                             @RequestParam(required = false,defaultValue = "1")
                                         int pageIndex, Model model){
        Sort sort=Sort.by(Sort.Direction.ASC,"chcId");
        Pageable pageable= PageRequest.of(pageIndex-1,5,sort);
        Page<salChance> chance=chanceService.findSalChance(chcCustName,chcDesc,pageable);

        model.addAttribute("chancePager",chance);
        model.addAttribute("chcCustName",chcCustName);
        model.addAttribute("chcDesc",chcDesc);
        return "/chance/list";
    }

    /**
     * 新增
     * @param
     * @return
     */
    @RequestMapping(value = "/chance/add")
    public String add(Model model, HttpSession session){
        return "/chance/add";
    }


    @RequestMapping(value = "/chance/addChance")
    public String addSalChance(salChance chance){
        salChance salChance1=chanceService.addSalChance(chance);
         if (salChance1!=null){
             return "redirect:/chance/list";
         }
        return "/chance/add";
    }

    /**
     * 修改
     * @return
     */
    @RequestMapping(value = "/chance/edit")
    public String edits(Long chcId,Model model){

        //按id查询  跳转到"/chance/regardChance"修改
        salChance chance=chanceService.findByChcId(chcId);
        model.addAttribute("chance",chance);
        return "/chance/edit";
    }


    @RequestMapping(value = "/chance/del")
    @ResponseBody
    @Transactional
    public Map delete(Long chcId){
        System.out.println(chcId);
        Map map= new HashMap();
        chanceService.deleteByAndChcId(chcId);
        map.put("delResult","true");
        return map;
    }


}
