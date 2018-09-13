package com.ysla.web.specification.generic;

/**
 * 组合规格书
 * @param <T>
 * @author konghang
 */
public abstract class CompositeSpecification<T> implements ISpecification<T> {

    public abstract boolean isSatisfiedBy(T t);

    @Override
    public ISpecification and(ISpecification spec) {
        return new AndSpecification(this, spec);
    }

    @Override
    public ISpecification or(ISpecification spec) {
        return new OrSpecification(this, spec);
    }

    @Override
    public ISpecification not() {
        return new NotSpecification(this);
    }

}
