package com.ysla.web.mybatis.sqlSession;

public interface IMySqlSession {

    <T> T selectOne(String var1);

    <T> T getMapper(Class<T> var1);

}
