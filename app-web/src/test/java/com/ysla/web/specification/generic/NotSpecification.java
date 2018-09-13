package com.ysla.web.specification.generic;

/**
 * not规格书
 * @param <T>
 * @author konghang
 */
public class NotSpecification<T> extends CompositeSpecification<T> {

    //传递两个规格书进行and操作
    private ISpecification spec;

    public NotSpecification(ISpecification spec){
        this.spec = spec;
    }

    //not运算
    @Override
    public boolean isSatisfiedBy(T t) {
        return !spec.isSatisfiedBy(t);
    }
}
