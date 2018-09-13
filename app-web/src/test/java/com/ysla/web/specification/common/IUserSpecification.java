package com.ysla.web.specification.common;

import com.ysla.web.specification.model.User;

/**
 * 规格书接口
 * @author konghang
 */
public interface IUserSpecification {

    boolean isSatisfiedBy(User user);

    IUserSpecification and(IUserSpecification spec);

    IUserSpecification or(IUserSpecification spec);

    IUserSpecification not();

}
