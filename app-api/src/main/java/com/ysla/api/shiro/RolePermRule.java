package com.ysla.api.shiro;

import com.ysla.utils.string.StringUtils;
import lombok.Getter;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * url和角色对应实体
 * @author konghang
 */
public class RolePermRule {

    /**
     * 资源URL
     */
    @Getter private String url;
    /**
     * 访问资源所需要的角色列表，多个列表用逗号间隔
     */
    @Getter private String needRoles;

    public StringBuilder toFilterChain() {

        if (null == this.url || this.url.isEmpty()){
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder();
        Set<String> setRole = new HashSet<>();
        setRole.addAll(Arrays.asList(this.getNeedRoles().split(",")));

        // 约定若role_anon角色拥有此uri资源的权限,则此uri资源直接访问不需要认证和权限
        if (!StringUtils.isEmpty(this.getNeedRoles()) && setRole.contains("role_anon")) {
            stringBuilder.append("anon");
        }
        //  其他自定义资源uri需通过jwt认证和角色认证
        if (!StringUtils.isEmpty(this.getNeedRoles()) && !setRole.contains("role_anon")) {
            stringBuilder.append("jwt"+"["+this.getNeedRoles()+"]");
        }

        return stringBuilder.length() > 0 ? stringBuilder : null;
    }

    @Override
    public String toString() {
        return "RolePermRule [url="+url+", needRoles="+needRoles+"]";
    }
}
