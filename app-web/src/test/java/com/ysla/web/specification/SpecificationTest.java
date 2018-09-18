package com.ysla.web.specification;

import com.ysla.web.specification.candidate.common.UserByAgeThan;
import com.ysla.web.specification.candidate.common.UserByNameEqual;
import com.ysla.web.specification.common.IUserSpecification;
import com.ysla.web.specification.generic.ISpecification;
import com.ysla.web.specification.model.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * 规格模式测试
 * https://blog.csdn.net/weiwangchao_/article/details/7892314
 * https://mp.weixin.qq.com/s/iTAQMoFDtbcORUvdm0vZAA
 * @author konghang
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class SpecificationTest {

    private List<User> users = new ArrayList<>();

    @Before
    public void before(){
        users.add(new User("苏国庆",10));
        users.add(new User("国庆牛",82));
        users.add(new User("张国庆三",10));
        users.add(new User("李四",26));
    }

    @Test
    public void common(){
        IUserSpecification spec = new UserByNameEqual("苏国庆");
        IUserSpecification spec2 = new UserByAgeThan(20);
        users.stream().filter(user -> spec.or(spec2).isSatisfiedBy(user))
                .forEach(user -> System.out.println(user.getName()));
    }

    @Test
    public void generic(){
        ISpecification spec = new com.ysla.web.specification.candidate.generic.UserByAgeThan<User>(30);

        users.stream().filter(user -> spec.not().isSatisfiedBy(user))
                .forEach(user -> System.out.println(user.getName()));
    }

}
