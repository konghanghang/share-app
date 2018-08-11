package com.ysla.api.module.user;

import com.alibaba.fastjson.JSONObject;
import com.ysla.api.auto.model.User;

/**
 * 用户api接口
 * @author konghang
 */
public interface IUserService {

    User selectUserBy(User user);

    JSONObject simpleInfo(String userId);

    JSONObject simpleInfo2(String userId);

}
