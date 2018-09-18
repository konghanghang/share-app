package com.ysla.web.mybatis.executor;

public interface IMyExecutor {

    <T> T query(String statement);

}
