package com.ysla.web.specification.common;

import com.ysla.web.specification.model.User;

/**
 * or规格书
 * @author konghang
 */
public class OrSpecification extends CompositeSpecification {

    //传递两个规格书进行and操作
    private IUserSpecification left;
    private IUserSpecification right;

    public OrSpecification(IUserSpecification left, IUserSpecification right){
        this.left = left;
        this.right = right;
    }

    //or运算
    @Override
    public boolean isSatisfiedBy(User user) {
        return left.isSatisfiedBy(user) || right.isSatisfiedBy(user);
    }
}
