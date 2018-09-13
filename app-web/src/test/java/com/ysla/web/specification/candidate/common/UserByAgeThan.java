package com.ysla.web.specification.candidate.common;

import com.ysla.web.specification.common.CompositeSpecification;
import com.ysla.web.specification.model.User;

/**
 * 通过年龄筛选用户
 * @author konghang
 */
public class UserByAgeThan extends CompositeSpecification {

    private Integer age;

    public UserByAgeThan(Integer age) {
        this.age = age;
    }

    //检验用户是否满足条件
    @Override
    public boolean isSatisfiedBy(User user) {
        return user.getAge() > age;
    }

}
