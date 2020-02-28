package com.t248.zjl.thymeleaf.shiro.ini;


import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Test;


public class ShiroTester {

    @Test
    public  void testLoginAndLogout(){
        //通过shiro.ini配置文件创建Realm
        IniRealm iniRealm=new IniRealm("classpath:shiro.ini");
        //创建SecurityManager
        DefaultSecurityManager defaultSecurityManager=new DefaultSecurityManager();
        defaultSecurityManager.setRealm(iniRealm);
        //将securityManager设置当前运行环境中
        //SecurityUtils：是shiro的一个工具类
        SecurityUtils.setSecurityManager(defaultSecurityManager);
        //从SecurityUtils里边创建要给subject
        //通过SecurityUtils获取getSubject得到一个返回值
        Subject subject=SecurityUtils.getSubject();
        //在认证提交准备token(令牌)
        UsernamePasswordToken token=new UsernamePasswordToken("admin","123");
        //执行认证提交
        try {
            //subject得返回值里有两个方法logou:登出login：登入AuthenticationToken得实现类来写
            subject.login(token);
        }catch (UnknownAccountException uae){
            System.out.println("未知账户异常"+uae);
        }catch (IncorrectCredentialsException ice){
            System.out.println("密码错误异常"+ice);
        }catch (LockedAccountException lae){
            System.out.println("账户锁定异常"+lae);
        }catch (ExcessiveAttemptsException eae){
            System.out.println("过多尝试次数异常"+eae);
        }catch (AuthenticationException ae){
            System.out.println("其他异常"+ae);
        }
        //是否认证通过
        boolean isAuthenticated=subject.isAuthenticated();
        System.out.println("是否认证通过:"+isAuthenticated);
        //退出操作
        subject.logout();
        isAuthenticated=subject.isAuthenticated();
        System.out.println("是否认证通过:"+isAuthenticated);
    }
}
