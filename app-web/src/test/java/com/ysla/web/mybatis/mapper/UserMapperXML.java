package com.ysla.web.mybatis.mapper;

import java.util.HashMap;
import java.util.Map;

/**
 * 为了不牵涉到XML的解析过程，直接提供已经处理完毕的结果。其实就是namespace/statementID/SQL的存储、映射。
 */
public class UserMapperXML {

    public static final String namespace = "com.ysla.web.mybatis.mapper.IUserMapper";

    private static Map<String,String> methodSqlMap = new HashMap<>();

    static {
        methodSqlMap.put("findUserById","select * from t_user where user_id = %s");
    }

    public static String getMethodSql(String method){
        return methodSqlMap.get(method);
    }

}
