package com.t248.zjl.thymeleaf.Controller;

import com.sun.deploy.net.URLEncoder;
import com.t248.zjl.thymeleaf.entity.CstCustomer;
import com.t248.zjl.thymeleaf.entity.CustGx;
import com.t248.zjl.thymeleaf.service.CustGxService;
import com.t248.zjl.thymeleaf.service.CustomerService;
import com.t248.zjl.thymeleaf.service.OrdersService;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class listConstituteController {

    @Autowired
    CustomerService customerService;
    @Resource
    OrdersService ordersService;
    @Resource
    CustGxService custGxService;


    @RequestMapping(value = "/statistics/listContribution")
    public String olist(@RequestParam(required = false, defaultValue = "1") int pageIndex, Model model,
                        @RequestParam(required = false) String custName) {

        Pageable pageable = PageRequest.of(pageIndex - 1, 2);
        if (custName == null) {
            custName = "";
        }

        List<CustGx> custGxes = custGxService.getCustGxAllBy(custName, pageIndex, 2);
        model.addAttribute("Page", custGxes);

        List<CustGx> custGxList = custGxService.findAllsd();
        model.addAttribute("custGxList", custGxList);
        return "statistics/listContribution";
    }

    @RequestMapping(value = "/statement/cal")
    @ResponseBody
    public Map statistical() {
        System.out.println("李明峰是儿子,不是大家的儿子");
        Map<String, Object> map = new HashMap<>();
        List<String> list = new ArrayList<>();
        List<Integer> list1 = new ArrayList<>();
        List<CustGx> custGxes = custGxService.findAllsd();
        for (CustGx custGx : custGxes) {
            list.add(custGx.getDictItem());
            list1.add(Integer.parseInt(custGx.getMoney()));
        }

        map.put("name", list);
        map.put("sum", list1);
        return map;
    }

    @RequestMapping(value = "/export/cst",method = RequestMethod.GET)
    public void goodsExcel(HttpServletResponse response){
        XSSFWorkbook wb =customerService.show();
        String fileName = "客户报表.xlsx";
        OutputStream outputStream =null;
        try {
            fileName = URLEncoder.encode(fileName,"UTF-8");
            //设置ContentType请求信息格式
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-disposition", "attachment;filename=" + fileName);
            outputStream = response.getOutputStream();
            wb.write(outputStream);
            outputStream.flush();
            outputStream.close();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
