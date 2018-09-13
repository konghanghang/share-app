package com.ysla.web.specification.common;

import com.ysla.web.specification.model.User;

/**
 * not规格书
 * @author konghang
 */
public class NotSpecification extends CompositeSpecification {

    //传递两个规格书进行and操作
    private IUserSpecification spec;

    public NotSpecification(IUserSpecification spec){
        this.spec = spec;
    }

    //not运算
    @Override
    public boolean isSatisfiedBy(User user) {
        return !spec.isSatisfiedBy(user);
    }
}
