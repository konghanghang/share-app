package com.ysla.web.config.shiro;

import com.ysla.web.config.shiro.filter.ShiroFilterChainManager;
import com.ysla.web.config.shiro.realm.JwtStatelessRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.DefaultSessionManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * shiro配置
 * @author konghang
 */
@Configuration
public class ShiroConfig {

    /**
     * 密码匹配凭证管理器
     * @return
     */
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        // 散列算法:这里使用MD5算法
        hashedCredentialsMatcher.setHashAlgorithmName("MD5");
        // 散列的次数
        hashedCredentialsMatcher.setHashIterations(1024);
        return hashedCredentialsMatcher;
    }

    /**
     * realm
     * @param matcher
     * @return
     */
    @Bean
    public JwtStatelessRealm jwtStatelessRealm(HashedCredentialsMatcher matcher) {
        JwtStatelessRealm jwtStatelessRealm = new JwtStatelessRealm();
        // 设置密码凭证匹配器
        /*jwtStatelessRealm.setCredentialsMatcher(matcher);*/
        return jwtStatelessRealm;
    }

    /**
     * 安全管理器
     * @param jwtStatelessRealm
     * @param sessionManager
     * @return
     */
    @Bean
    public SecurityManager securityManager(JwtStatelessRealm jwtStatelessRealm,
                                           SessionManager sessionManager) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        // 设置realm
        securityManager.setRealm(jwtStatelessRealm);
        securityManager.setSessionManager(sessionManager);
        // 在无状态应用中需要禁用将Subject状态持久化到会话,需要注意的是，禁用使用Sessions 作为存储策略的实现，
        // 但它没有完全地禁用Sessions。如果你的任何代码显式地调用subject.getSession()或subject.getSession(true)，
        // 一个session 仍然会被创建。https://blog.csdn.net/peterwanghao/article/details/8295620
        /*((DefaultSessionStorageEvaluator)(((DefaultSubjectDAO)securityManager
                .getSubjectDAO())
                .getSessionStorageEvaluator()))
                .setSessionStorageEnabled(false);*/
        DefaultSubjectDAO subjectDAO = (DefaultSubjectDAO)securityManager.getSubjectDAO();
        DefaultSessionStorageEvaluator evaluator = (DefaultSessionStorageEvaluator) subjectDAO.getSessionStorageEvaluator();
        StatelessDefaultSubjectFactory subjectFactory = new StatelessDefaultSubjectFactory(evaluator);
        securityManager.setSubjectFactory(subjectFactory);
        return securityManager;
    }

    /**
     * 会话管理器-定期检查session设置为false
     * @return
     */
    @Bean
    public SessionManager sessionManager(){
        DefaultSessionManager sessionManager = new DefaultSessionManager();
        sessionManager.setSessionValidationSchedulerEnabled(false);
        return sessionManager;
    }

    /**
     * shiroFilter
     * @param securityManager
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager, ShiroFilterChainManager chainManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        // 必须设置 SecurityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        // 设置自定义filter
        shiroFilterFactoryBean.setFilters(chainManager.initFilters());
        // TODO 拦截器 动态加载
        Map<String, String> map = new LinkedHashMap<>();
        map.put("/logout", "logout");
        map.put("/api/user/login", "anon");

        map.put("/**", "jwt");

        // 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
        shiroFilterFactoryBean.setLoginUrl("/login");
        // 登录成功后要跳转的链接
        shiroFilterFactoryBean.setSuccessUrl("/index");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
        return shiroFilterFactoryBean;
    }

    /**
     * Shiro生命周期处理器
     * @return
     */
    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor(){
        return new LifecycleBeanPostProcessor();
    }

    /**
     * 开启shiro注解
     * @return
     */
    @Bean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator(){
        DefaultAdvisorAutoProxyCreator creator=new DefaultAdvisorAutoProxyCreator();
        creator.setProxyTargetClass(true);
        return creator;
    }

    /**
     * 开启shiro注解
     * @param manager
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager manager) {
        AuthorizationAttributeSourceAdvisor advisor=new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(manager);
        return advisor;
    }

}
