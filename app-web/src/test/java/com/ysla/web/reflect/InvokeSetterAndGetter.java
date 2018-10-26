package com.ysla.web.reflect;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;

/**
 * 参考:https://www.cnblogs.com/jason123/p/7092008.html
 * @author konghang
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class InvokeSetterAndGetter {

    @Test
    public void test() throws Exception{
        //Class clazz = Class.forName("com.ysla.web.reflect.User");
        //Object obj = clazz.newInstance();
        User user = new User();
        user.setUsername("konghang");

        PropertyDescriptor pd = new PropertyDescriptor("username", User.class);
        Method get = pd.getReadMethod();
        String name = (String)get.invoke(user);
        Assert.assertEquals("konghang",name);
        Method write = pd.getWriteMethod();
        write.invoke(user,"konghang1");
        name = get.invoke(user).toString();
        Assert.assertEquals("konghang1",name);
    }

}
