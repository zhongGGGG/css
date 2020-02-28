package com.t248.zjl.thymeleaf.Controller;

import com.t248.zjl.thymeleaf.entity.Right;
import com.t248.zjl.thymeleaf.entity.Role;
import com.t248.zjl.thymeleaf.entity.SysRoleRight;
import com.t248.zjl.thymeleaf.entity.User;
import com.t248.zjl.thymeleaf.service.RightService;
import com.t248.zjl.thymeleaf.service.RoleRightService;
import com.t248.zjl.thymeleaf.service.RoleService;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/role")
public class RoleController {
    @Resource
    private RoleService roleService;
    @Resource
    private RightService rightService;
    @Resource
    private RoleRightService roleRightService;

    @RequestMapping(value = "/json")
    @ResponseBody
    public List<Role> findAllRoles(){
        List<Role> list = roleService.findAllRoles();
        return list;
    }

    @RequestMapping(value = "/list")
    public String findUsers(String roleName, Model model,
                            @RequestParam(required = false,defaultValue = "1")int pageIndex){
        Pageable pageable = PageRequest.of(pageIndex-1,2);
        Page<Role> userPager = roleService.findRoles(roleName,pageable);
        model.addAttribute("userPager",userPager);
        model.addAttribute("roleName",roleName);
        return "role/list";
    }

    @RequestMapping(value = "/save")
    public String save(String roleName, String[] parentRight, HttpSession session){
        User user = (User) session.getAttribute("user");
        Role role=new Role();
        role.setRoleFlag(user.getUsrFlag());
        role.setRoleDesc("普通用户");
        role.setRoleName(roleName);
        Role role1 =roleService.addRole(role);
        System.out.println(role1.getRoleId());
        for (String s:parentRight ){
            SysRoleRight roleRight=new SysRoleRight();
//            roleRight.setRfRightCcode(s);
            roleRight.setRfRightCode(s);
            roleRight.setRfRoleId(role1.getRoleId());
            roleRightService.save(roleRight);
        }
        return "redirect:/role/list";
    }
    @RequestMapping(value = "/update")
    public String save1(Role role, String[] parentRight, HttpSession session){
        User user = (User) session.getAttribute("user");
        role.setRoleFlag(user.getUsrFlag());

        Role role1 =roleService.addRole(role);
        for (String s:parentRight ){
            SysRoleRight roleRight=new SysRoleRight();
//            roleRight.setRfRightCcode(s);
            roleRight.setRfRightCode(s);
            roleRight.setRfRoleId(role1.getRoleId());
            roleRightService.save(roleRight);
        }
        return "redirect:/role/list";
    }
    @RequestMapping(value = "/add")
    public String add(Model model){
        List<Right> rights=rightService.findAllRights();
        model.addAttribute("rights",rights);
        return "role/add";
    }

    @RequestMapping(value = "/edit")
    public String edit(Model model,Long roleId){
        System.out.println(roleId+"0.0");
        Role role = roleService.finadAll(roleId);
        roleRightService.delete(roleId);
        List<Right> rights=rightService.findAllRights();
        model.addAttribute("rights",rights);
        model.addAttribute("role",role);
        return "role/edit";
    }

    @RequestMapping(value = "/del")
    @ResponseBody
    public Map del(Long roleId){
        roleService.deleteRole(roleId);
        Map map = new HashMap();
        map.put("delResult","true");
        return map;
    }


}
