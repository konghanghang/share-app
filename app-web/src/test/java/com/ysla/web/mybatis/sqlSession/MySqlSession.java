package com.ysla.web.mybatis.sqlSession;

import com.ysla.web.mybatis.mapper.MyMapperProxy;
import com.ysla.web.mybatis.executor.IMyExecutor;
import com.ysla.web.mybatis.executor.MyExecutor;

import java.lang.reflect.Proxy;

public class MySqlSession implements IMySqlSession {

    IMyExecutor myExecutor = new MyExecutor();

    @Override
    public <T> T selectOne(String var1) {
        return myExecutor.query(var1);
    }

    @Override
    public <T> T getMapper(Class<T> var1) {
        return (T) Proxy.newProxyInstance(var1.getClassLoader(),new Class[]{var1},
                new MyMapperProxy(this));
    }
}
