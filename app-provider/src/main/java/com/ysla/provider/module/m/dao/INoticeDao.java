package com.ysla.provider.module.m.dao;

import com.ysla.api.auto.mapper.MensesMapper;
import com.ysla.api.auto.model.Menses;
import com.ysla.api.auto.model.NoticeTemplate;
import com.ysla.api.auto.model.WxTemplate;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 自用mapper扩展类,处理一些事情
 * @author konghang
 */
@Mapper
public interface INoticeDao extends MensesMapper {

    /**
     * 选择列表
     * @param openId
     * @return
     */
    @Select({"select * from m_menses where open_id = #{openId} order by createDate desc"})
    List<Menses> showMenses(String openId);

    /**
     * 获取所有的notice模板
     * @return
     */
    @Select({"select * from m_notice_template"})
    List<NoticeTemplate> allTemplate();

    /**
     * 获取微信模板消息
     * @param templateId
     * @return
     */
    @Select({"select * from m_wx_template where wx_template_id = #{templateId}"})
    WxTemplate selectByWx(String templateId);

}
