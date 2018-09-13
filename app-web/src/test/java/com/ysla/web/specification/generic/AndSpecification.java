package com.ysla.web.specification.generic;

/**
 * and规格书
 * @param <T>
 * @author konghang
 */
public class AndSpecification<T> extends CompositeSpecification<T> {

    //传递两个规格书进行and操作
    private ISpecification left;
    private ISpecification right;

    public AndSpecification(ISpecification left, ISpecification right){
        this.left = left;
        this.right = right;
    }

    //进行and运算
    @Override
    public boolean isSatisfiedBy(T t) {
        return left.isSatisfiedBy(t) && right.isSatisfiedBy(t);
    }
}
