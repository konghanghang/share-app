package com.ysla.web.specification.candidate.common;

import com.ysla.web.specification.common.CompositeSpecification;
import com.ysla.web.specification.model.User;

/**
 * 通过名字筛选用户
 * @author konghang
 */
public class UserByNameEqual extends CompositeSpecification {

    private String name;

    public UserByNameEqual(String name) {
        this.name = name;
    }

    //检验用户是否满足条件
    @Override
    public boolean isSatisfiedBy(User user) {
        return user.getName().equals(name);
    }
}
