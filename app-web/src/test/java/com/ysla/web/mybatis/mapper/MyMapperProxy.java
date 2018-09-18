package com.ysla.web.mybatis.mapper;

import com.ysla.web.mybatis.sqlSession.IMySqlSession;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MyMapperProxy implements InvocationHandler {

    IMySqlSession mySqlSession;

    public MyMapperProxy(IMySqlSession mySqlSession) {

        this.mySqlSession = mySqlSession;

    }

    /**
     * 当invoke方法被调用时，我们根据调用的方法，进行反射，
     * 得到namespace以及对应的SQL，然后，我们把SQL交给sqlSession进行执行即可。
     * @param proxy
     * @param method
     * @param args
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String mapperClass = method.getDeclaringClass().getName();

        if (UserMapperXML.namespace.equals(mapperClass)){
            String methodName = method.getName();
            String originSql = UserMapperXML.getMethodSql(methodName);

            String formatSql = String.format(originSql,String.valueOf(args[0]));

            return mySqlSession.selectOne(formatSql);
        }

        return null;
    }
}
