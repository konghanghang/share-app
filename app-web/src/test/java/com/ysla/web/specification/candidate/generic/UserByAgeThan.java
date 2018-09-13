package com.ysla.web.specification.candidate.generic;

import com.ysla.web.specification.generic.CompositeSpecification;
import com.ysla.web.specification.model.User;

/**
 * 通过年龄筛选用户
 * @param <T>
 * @author konghang
 */
public class UserByAgeThan<T> extends CompositeSpecification<T> {

    private Integer age;

    public UserByAgeThan(Integer age) {
        this.age = age;
    }

    //检验用户是否满足条件
    @Override
    public boolean isSatisfiedBy(T t) {
        return ((User)t).getAge() > age;
    }

}
