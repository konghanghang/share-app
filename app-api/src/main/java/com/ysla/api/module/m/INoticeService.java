package com.ysla.api.module.m;

import com.ysla.api.model.exception.TxException;
import com.ysla.api.model.page.PageModel;

/**
 * 自用api,处理一些事情
 * @author konghang
 */
public interface INoticeService {

    /**
     * 新增一条记录
     * @param openId
     * @throws TxException
     */
    void addMenses(String openId) throws TxException;

    /**
     * 展示所有的记录
     * @param openId
     * @param pageModel
     * @return
     */
    PageModel showMenses(String openId, PageModel pageModel);

    /**
     * 发送通知
     * @param openId
     * @return
     */
    String sendNotice(String openId);

}
