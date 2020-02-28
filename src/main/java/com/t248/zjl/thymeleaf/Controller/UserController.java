package com.t248.zjl.thymeleaf.Controller;

import com.t248.zjl.thymeleaf.entity.Role;
import com.t248.zjl.thymeleaf.entity.User;
import com.t248.zjl.thymeleaf.service.RoleService;
import com.t248.zjl.thymeleaf.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
public class UserController {
    @Resource
    private UserService userService;
    @Autowired
    private RoleService roleService;

    @GetMapping(value = "/hello/{id}")
    public String getUser(@PathVariable("id") Long userId, Model model) {
        User user = userService.findByUsrId(userId);
        model.addAttribute("user", user);
        return "hello";
    }

    /**
     * 判断登录
     * @param usrName
     * @param usrPassword
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value = "/dologin")
    public String getName(String usrName, String usrPassword, Model model, HttpServletRequest request,
                Map<String,Object> map,HttpSession session) {
//        User user = userService.findByUsrName(usrName, usrPassword);
//       if(user!=null){
//           request.getSession().setAttribute("user", user);
//           return "main";
//       }else {
//           request.setAttribute("message","登录失败,用户名或者密码错误！");
//           return "login";
//       }

        try{
            //此处不再处理登录，由shiro进行处理
            AuthenticationToken token=new UsernamePasswordToken(usrName,usrPassword);
            SecurityUtils.getSubject().login(token);//调用Shrio认证
            User user= (User) SecurityUtils.getSubject().getPrincipal();
            //注意此处session.setAttribute中Key的值
            //需要和AuthorizationInterceptor拦截器session的Key一直
            session.setAttribute("user",user);
        }catch (IncorrectCredentialsException i){
            i.printStackTrace();
            map.put("msg","密码错误:"+i.getMessage());
            return "login";
        }
        catch (Exception e){
            e.printStackTrace();
            model.addAttribute("msg",e.getMessage());
            return "login";
        }
        return "main";
    }

    /**
     * 登录页面
     * @return
     */
    @RequestMapping(value = "/login")
    public String login(){
        return "login";
    }

    @RequestMapping(value = "/main")
    public String main(){
        return "main";
    }


    @RequestMapping("/403")
    public String unauthorizedRole(){
        System.out.println("-----------权限-----------");
        return "403";
    }

    /**
     * 退出
     * @return
     */
    @RequestMapping(value = "/logout")
    public String logout(HttpSession session){
        //删除session
        session.removeAttribute("user");
        //调用登出方法
        SecurityUtils.getSubject().logout();
        return "login";
    }
    /**
     * 用户管理页面
     * @param usrName
     * @param roleId
     * @param model
     * @return
     */
    @RequestMapping(value = "/user/list")
    public String list(String usrName, @RequestParam(required = false,defaultValue = "0")
                          Long roleId,  @RequestParam(required = false,defaultValue = "1")
           int pageIndex, Model model){
        Sort sort=Sort.by(Sort.Direction.ASC,"usrId");
        Pageable pageable= PageRequest.of(pageIndex-1,5,sort);
        Page<User> userPage=userService.findUsers(usrName,roleId,pageable);

        model.addAttribute("userPager",userPage);
        model.addAttribute("usrName",usrName);
        model.addAttribute("roleId",roleId);
        List<Role> roles=roleService.findAllRoles();
        model.addAttribute("roles",roles);
        return "/user/list";
    }
    @GetMapping(value = "/user/add")
    public String add(Model model){
        //新增用户的用户角色加载
        List<Role> roles=roleService.findAllRoles();
        model.addAttribute("roles",roles);
        return "/user/add";
    }
    @RequestMapping(value = "user/save")
    public String save(User user){
        //新增用户账号密码
        User user1=userService.addUser(user);
        if (user1!=null){
            return "redirect:/user/list";
        }
        return "user/save";
    }
    @RequestMapping(value = "/user/edit")
    public String edit(Long usrId,Model model){
        //按id查询  跳转到"user/save"修改
        User user1=userService.findById(usrId);
        model.addAttribute("user",user1);

        List<Role> roles=roleService.findAllRoles();
        model.addAttribute("roles",roles);
        return "user/edit";
    }
    @RequestMapping(value = "/user/del")
    @ResponseBody
    public Map del(Long usrId){
        Map map=new HashMap();
        User user=userService.deleteUser(usrId);

        map.put("delResult","true");
        return map;
    }

}
