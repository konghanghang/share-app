package com.ysla.web.config.shiro.filter;

import com.ysla.api.shiro.RolePermRule;
import com.ysla.web.config.shiro.rest.RestPathMatchingFilterChainResolver;
import com.ysla.web.support.SpringContextHolder;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.mgt.DefaultFilterChainManager;
import org.apache.shiro.web.servlet.AbstractShiroFilter;
import org.springframework.stereotype.Component;

import javax.servlet.Filter;
import java.util.*;

/**
 * 过滤器以及过滤url管理
 * @author konghang
 */
@Component
public class ShiroFilterChainManager {

    /**
     * 初始化获取过滤链
     */
    public Map<String, Filter> initFilters() {
        Map<String, Filter> filters = new LinkedHashMap<>();
        JwtStatelessFilter jwtFilter = new JwtStatelessFilter();
        filters.put("jwt",jwtFilter);
        return filters;
    }

    /**
     * 初始化获取过滤链规则
     * @return
     */
    public Map<String,String> initFilterChain() {
        Map<String,String> filterChain = new LinkedHashMap<>();
        // -------------anon 默认过滤器忽略的URL
        List<String> defaultAnon = Arrays.asList("/css/**","/js/**");
        defaultAnon.forEach(ignored -> filterChain.put(ignored,"anon"));
        // -------------dynamic 动态URL
        // TODO: 2018/8/9  rolePermRules动态去数据库加载
        List<RolePermRule> rolePermRules = new ArrayList<>();
        if (null != rolePermRules) {
            rolePermRules.forEach(rule -> {
                StringBuilder Chain = rule.toFilterChain();
                if (null != Chain) {
                    filterChain.putIfAbsent(rule.getUrl(),Chain.toString());
                }
            });
        }
        return filterChain;
    }

    /**
     * 动态重新加载过滤链规则
     */
    public void reloadFilterChain() {
        ShiroFilterFactoryBean shiroFilterFactoryBean = SpringContextHolder.getBean(ShiroFilterFactoryBean.class);
        AbstractShiroFilter abstractShiroFilter = null;
        try {
            abstractShiroFilter = (AbstractShiroFilter)shiroFilterFactoryBean.getObject();
            RestPathMatchingFilterChainResolver filterChainResolver = (RestPathMatchingFilterChainResolver)abstractShiroFilter.getFilterChainResolver();
            DefaultFilterChainManager filterChainManager = (DefaultFilterChainManager)filterChainResolver.getFilterChainManager();
            filterChainManager.getFilterChains().clear();
            shiroFilterFactoryBean.getFilterChainDefinitionMap().clear();
            shiroFilterFactoryBean.setFilterChainDefinitionMap(this.initFilterChain());
            shiroFilterFactoryBean.getFilterChainDefinitionMap().forEach((k,v) -> filterChainManager.createChain(k,v));
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
