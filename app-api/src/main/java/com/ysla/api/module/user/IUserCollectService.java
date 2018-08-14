package com.ysla.api.module.user;

import com.ysla.api.auto.model.UserCollect;
import com.ysla.api.model.exception.TxException;
import com.ysla.api.model.page.PageModel;

/**
 * 收藏api
 * @author konghang
 */
public interface IUserCollectService {

    /**
     * 收藏
     * @param userCollect
     * @return
     */
    int collect(UserCollect userCollect) throws TxException;

    /**
     * 删除收藏
     * @param collectId
     * @param type
     * @return
     * @throws TxException
     */
    int deleteCollect(String collectId, byte type) throws TxException;

    /**
     * 用户收藏的文章列表
     * @param username
     * @param pageModel
     * @return
     */
    PageModel articleCollects(String username, PageModel pageModel);

}
