package com.t248.zjl.thymeleaf.config.shiro;


import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import com.t248.zjl.thymeleaf.entity.Right;
import com.t248.zjl.thymeleaf.service.UserService;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import javax.annotation.Resource;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class ShiroConfig {

    @Resource
    private UserService userService;
    /**
     * 创建ShiroFilterFactoryBean
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager){
        System.out.println("ShiroFilterFactoryBean.shiroFilter()：配置权限控制规则");
        ShiroFilterFactoryBean shiroFilterFactoryBean=new ShiroFilterFactoryBean();
        //必须设置SecurityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        //如果不设置默认会自动寻找WEB工程根目录下的”/login.jsp“页面
        shiroFilterFactoryBean.setLoginUrl("/login");
        //登录成功后要跳转的链接
        shiroFilterFactoryBean.setSuccessUrl("/main");
        //未授权界面
        shiroFilterFactoryBean.setUnauthorizedUrl("/403");
        //添加shiro内置过滤器，实现权限相关的url拦截
        /**
         * 常见过滤器
         * anon:无需认证（登录）可以访问
         * authc:必须认证才可以访问
         * user:如果使用Remember Me的功能，可以直接访问
         * perms:该资源必须得到资源权限才可以访问
         * role:该资源必须得到角色权限才可以访问
         */
        Map<String,String> filterChainDefinitionMap=new LinkedHashMap<String, String>();
        //配置不会被拦截的链接顺序判断
        filterChainDefinitionMap.put("/css/**","anon");
        filterChainDefinitionMap.put("/fonts/**","anon");
        filterChainDefinitionMap.put("/images/**","anon");
        filterChainDefinitionMap.put("/js/**","anon");
        filterChainDefinitionMap.put("/localcss/**","anon");
        filterChainDefinitionMap.put("/dologin/**","anon");
        filterChainDefinitionMap.put("/logout/**","logout");
        //配置测试权限（真实权限应该从数据库中获得）
//        filterChainDefinitionMap.put("/user/list","perms[用户管理]");
//        filterChainDefinitionMap.put("/user/add","perms[用户添加]");
//        filterChainDefinitionMap.put("/role/list","perms[角色管理]");

        //从数据库中动态获取所有权限控制(控制URL访问)
        List<Right> rights=userService.findAllRights();//获得所有权限控制
        for (Right right:rights){
            if (!right.getRight_type().equals("Folder")&&!right.getRight_type().equals("Button")){
                System.out.println("过滤器拦截得url："+right.getRightUrl()+",以及对应需要访问得权限:"+right.getRightText());
                filterChainDefinitionMap.put(right.getRightUrl(),"perms["+right.getRightText()+"]");
            }
        }

        //过滤链定义，从上向下顺序执行，一般将/**放在最为下行：这是一个坑呢，一不小心代码就不好使了;
        //authc:所有url都有必须认证通过才可以访问；anon所有：url都都可以匿名访问
        filterChainDefinitionMap.put("/**","authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    /**
     * 创建Realm
     * @return
     */
    @Bean
    public MyShiroRealm myShiroRealm(){
        MyShiroRealm myShiroRealm=new MyShiroRealm();
        //告诉realm使用credentialsMatcher加密算法类来验证密文
        myShiroRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        //启动缓存设置缓存名称
        myShiroRealm.setCachingEnabled(true);
        myShiroRealm.setAuthorizationCachingEnabled(true);
        myShiroRealm.setAuthenticationCacheName("authorizationCache");
        return myShiroRealm;
    }

    /**
     * 创建DefaultWebSecurityManager
     * @return
     */
    @Bean
    public SecurityManager securityManager(){
        DefaultWebSecurityManager securityManager=new DefaultWebSecurityManager();
        //设置realm
        securityManager.setRealm(myShiroRealm());
        //自定义缓存实现 使用redis
        securityManager.setCacheManager(cacheManager());
        //自定义session管理使用redis
        securityManager.setSessionManager(sessionManager());
        return securityManager;
    }

    /**
     * 配置shi'ro redisManager
     * 使用的是shiro-redis开源插入
     *
     * @return
     */
    public RedisManager redisManager(){
        RedisManager redisManager=new RedisManager();
        redisManager.setHost(host);
        redisManager.setPort(port);
//        redisManager.setPassword(password);
        redisManager.setTimeout(timeout);
        return redisManager;
    }

    /**
     * cacheManger 缓存redis实现
     * 使用的是shiro-redis开源插件
     * @return
     */
    public RedisCacheManager cacheManager(){
        RedisCacheManager redisCacheManager=new RedisCacheManager();
        redisCacheManager.setRedisManager(redisManager());
        //缓存名称
        redisCacheManager.setPrincipalIdFieldName("usrName");
        //缓存时间
        redisCacheManager.setExpire(1800);
        return redisCacheManager;
    }

    /**
     * RedisSessionDAO shiro sessionDao层的实现 通过redis
     *  使用的是shiro-redis开源插件
     * @return
     */
    @Bean
    public RedisSessionDAO redisSessionDAO(){
        RedisSessionDAO redisSessionDAO=new RedisSessionDAO();
        redisSessionDAO.setRedisManager(redisManager());
        return redisSessionDAO;
    }

    @Bean
    public DefaultWebSessionManager sessionManager(){
        DefaultWebSessionManager sessionManager=new DefaultWebSessionManager();
        sessionManager.setSessionDAO(redisSessionDAO());
        return sessionManager;
    }

    /**
     * 配置thymeleaf页面对shiro标签的支持
     * @return
     */
    @Bean(name = "shiroDialect")
    public ShiroDialect shiroDialect(){
        return new ShiroDialect();
    }


    //注入redis参数
    @Value("${spring.redis.host}")
    private String host;
    @Value("${spring.redis.port}")
    private int port;
//    @Value("${spring.redis.password}")
//    private String password;
    @Value("${spring.redis.timeout}")
    private int timeout;


    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher(){
        System.out.println("hashedCredentialsMatcher.........");
        HashedCredentialsMatcher hashedCredentialsMatcher=new HashedCredentialsMatcher();
        //使用md5算法进行加密、
        hashedCredentialsMatcher.setHashAlgorithmName("md5");
        //设置散列次数  意为加密几次
        hashedCredentialsMatcher.setHashIterations(1);
        return hashedCredentialsMatcher;
    }

}
