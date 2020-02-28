package com.t248.zjl.thymeleaf.Controller;

import com.t248.zjl.thymeleaf.entity.BasDict;
import com.t248.zjl.thymeleaf.entity.CstCustomer;
import com.t248.zjl.thymeleaf.entity.CstLinkman;
import com.t248.zjl.thymeleaf.service.BasDictService;
import com.t248.zjl.thymeleaf.service.CustomerService;
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

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.util.List;

@Controller
public class CustomerController {

    @Resource
    CustomerService customerService;
    @Resource
    BasDictService basDictService;

    /**
     * 分页与模糊查询
     * @param custName
     * @param custNo
     * @param custRegion
     * @param custManagerName
     * @param custLevelLabel
     * @param pageIndex
     * @param model
     * @return
     */
    @RequestMapping(value = "/customer/list")
    public String getChcCustName(@RequestParam(required = false) String custName, @RequestParam(required = false)
            String custNo,@RequestParam(required = false)
            String custRegion,@RequestParam(required = false)
            String custManagerName,@RequestParam(required = false)
            String custLevelLabel, @RequestParam(required = false,defaultValue = "1")
                                     int pageIndex, Model model){
        Sort sort=Sort.by(Sort.Direction.ASC,"custId");
        Pageable pageable= PageRequest.of(pageIndex-1,5,sort);
        Page<CstCustomer> cstCustomers=customerService.findAll(custName,custNo,custRegion,custManagerName,custLevelLabel,pageable);
        model.addAttribute("customerPage",cstCustomers);
        model.addAttribute("custName",custName);
        model.addAttribute("custNo",custNo);
        model.addAttribute("custManagerName",custManagerName);
        model.addAttribute("custLevelLabel",custLevelLabel);
        /**
         * 地区跟客户等级
         */
        List<BasDict> basDicts=basDictService.findBasDictsByAndDictType("地区");
        List<BasDict> dengji=basDictService.findBasDictsByAndDictType("客户等级");
        model.addAttribute("basDicts",basDicts);
        model.addAttribute("dengji",dengji);
        return "/customer/list";
    }

    /**
     * 新增
     * @param cstCustomer
     * @param model
     * @return
     */
    @RequestMapping(value = "/customer/addCust")
    public String addedit(CstCustomer cstCustomer,Model model){
        CstCustomer cstCustomer1=customerService.addCstCustomer(cstCustomer);
        return "redirect:/customer/list";
    }


    /**
     * 修改
     * @param model
     * @param custId
     * @return
     */
    @RequestMapping(value = "/customer/edit")
    public String edite(Model model,Long custId){

        CstCustomer cstCustomer=customerService.findBycustId(custId);
        model.addAttribute("cstCustomer",cstCustomer);
        /**
         * 地区跟客户等级
         */
        List<BasDict> basDicts=basDictService.findBasDictsByAndDictType("地区");
        List<BasDict> dengji=basDictService.findBasDictsByAndDictType("客户等级");
        model.addAttribute("basDicts",basDicts);
        model.addAttribute("dengji",dengji);
        return "/customer/edit";
    }

    /**
     * 查询联系人
     * @param model
     * @param custId
     * @return
     */
    @RequestMapping(value = "contacts/save")
    public String savc(Model model,Long custId){
        CstCustomer cstCustomer=customerService.findBycustId(custId);
        model.addAttribute("cstCustomer",cstCustomer);

        return "/customer/contacts";
    }





}
