package com.ysla.web.specification.common;

import com.ysla.web.specification.model.User;

/**
 * 组合规格书
 * @author konghang
 */
public abstract class CompositeSpecification implements IUserSpecification {

    public abstract boolean isSatisfiedBy(User user);

    @Override
    public IUserSpecification and(IUserSpecification spec) {
        return new AndSpecification(this, spec);
    }

    @Override
    public IUserSpecification or(IUserSpecification spec) {
        return new OrSpecification(this, spec);
    }

    @Override
    public IUserSpecification not() {
        return new NotSpecification(this);
    }

}
