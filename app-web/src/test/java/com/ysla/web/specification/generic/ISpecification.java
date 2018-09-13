package com.ysla.web.specification.generic;

/**
 * 规格书接口
 * @param <T>
 * @author konghang
 */
public interface ISpecification<T> {

    boolean isSatisfiedBy(T t);

    ISpecification and(ISpecification spec);

    ISpecification or(ISpecification spec);

    ISpecification not();

}
