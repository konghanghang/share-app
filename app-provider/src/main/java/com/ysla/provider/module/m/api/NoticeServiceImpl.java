package com.ysla.provider.module.m.api;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.ysla.api.auto.model.Menses;
import com.ysla.api.auto.model.NoticeTemplate;
import com.ysla.api.auto.model.WxTemplate;
import com.ysla.api.model.common.ErrorCode;
import com.ysla.api.model.common.NumEnum;
import com.ysla.api.model.common.StringEnum;
import com.ysla.api.model.exception.TxException;
import com.ysla.api.model.page.PageModel;
import com.ysla.api.model.wx.api.WechatApi;
import com.ysla.api.module.m.INoticeService;
import com.ysla.api.module.wx.mp.IWxMpService;
import com.ysla.api.utils.http.HttpClientUtils;
import com.ysla.provider.module.m.dao.INoticeDao;
import com.ysla.utils.date.DateUtils;
import com.ysla.utils.string.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 自用api的实现,处理一些事情
 * @author konghang
 */
@Slf4j
@Component
@Service(version = "${dubbo.service.version}")
public class NoticeServiceImpl implements INoticeService {

    @Value("${com.ysla.wechat.appId}")
    private String appId;
    @Resource
    private INoticeDao noticeDao;
    @Resource
    private IWxMpService mpService;

    /**
     * 新增一条记录
     * @param openId
     * @throws TxException
     */
    @Override
    @Transactional(rollbackFor = TxException.class)
    public void addMenses(String openId) throws TxException {
        PageHelper.startPage(1,1);
        List<Menses> list = noticeDao.showMenses(openId);
        long now = DateUtils.getUnixStamp();
        //当天00:00:00的时间戳
        long begin = DateUtils.getDayStartTime();
        //设置时间为当天10点
        long trueMensesTime = begin + 60 * 60 * 10;
        if (list != null && list.size() > 0){
            Menses menses = list.get(0);
            menses.setTrueMensesTime(trueMensesTime);
            if (noticeDao.updateByPrimaryKey(menses) < 1){
                throw new TxException(ErrorCode.TRANSACTION_ERROR);
            }
        }

        LocalDate localDate = LocalDate.now();
        Menses menses1 = new Menses();
        menses1.setYear(localDate.getYear() + "");
        menses1.setMonth(localDate.getMonth() + "");
        menses1.setOpenId(openId);
        menses1.setLastMensesTime(trueMensesTime);
        LocalDateTime localDateTime = LocalDateTime.of(localDate.getYear(),
                localDate.getMonth().getValue(),
                localDate.getDayOfMonth(),22,0,0)
                .plusDays(27);
        menses1.setMensesTime(localDateTime.toEpochSecond(ZoneOffset.ofHours(8)));
        menses1.setCreateDate(now);
        if (noticeDao.insertSelective(menses1) < 1){
            throw new TxException(ErrorCode.TRANSACTION_ERROR);
        }
    }

    /**
     * 展示所有的记录
     * @param openId
     * @param pageModel
     * @return
     */
    @Override
    public PageModel showMenses(String openId, PageModel pageModel) {
        PageHelper.startPage(pageModel.getPageNum(), pageModel.getPageSize());
        List<Menses> list =  noticeDao.showMenses(openId);
        return pageModel.list(list);
    }

    /**
     * 发送通知
     * @param openId
     * @return
     */
    @Override
    public String sendNotice(String openId) {
        Long today = DateUtils.getDayStartTime();
        PageHelper.startPage(1,1);
        Menses menses = noticeDao.showMenses(openId).get(0);
        int intervalDays = DateUtils.getBetweenDay(today,menses.getMensesTime());
        String dateStr = DateUtils.timeStampToStr(menses.getMensesTime());
        String title = "定时任务执行:" + dateStr;
        String content = "定时任务执行,但是没有发送,还有:" + intervalDays + "天";
        if(intervalDays == NumEnum.TWO.getNum()
                || intervalDays == NumEnum.FIVE.getNum()
                || intervalDays == NumEnum.SEVEN.getNum()){
            List<NoticeTemplate> list = noticeDao.allTemplate();
            String notice = list.get(StringUtils.generateRandomNum(list.size())).getNotice();
            WxTemplate msgTemplate = noticeDao.selectByWx("IFAq7xEzC0l5C9f1fYs-7A15VEk5nMhSHwgRxaUOFs0");
            notice = msgTemplate.getRemark().replace("DAY",intervalDays+"") + notice;

            String template = wrapTemplate(msgTemplate,menses,intervalDays,notice,openId);
            String url = WechatApi.SEND_TM.getUrl().replace(StringEnum.ACCESS_TOKEN.getName(),mpService.getAccessToken(appId));
            JSONObject jsonObject = HttpClientUtils.httpPostJson(url, template);
            title = "定时任务发送成功:"+dateStr;
            content = "发送成功,还有" + intervalDays + "天";
            if(!"0".equals(jsonObject.getString(StringEnum.ERR_CODE.getName()))){
                log.error("sendTemplateMsgInfo:error");
                title = "定时任务发送失败:"+dateStr;
                content = jsonObject.toString();
            }
        }
        //mailService.sendMail("kong.hh@live.com",title,content);
        return null;
    }

    private String wrapTemplate(WxTemplate wxTemplate, Menses menses, Integer day, String notice, String openId){
        Map<String, Object> map = new HashMap<>(16);
        Map<String, JSONObject> mapData = new HashMap<>(16);
        Map<String, String> mapFirst = new HashMap<>(16);
        Map<String, String> mapKeynote1 = new HashMap<>(16);
        Map<String, String> mapKeynote2 = new HashMap<>(16);
        Map<String, String> mapKeynote3 = new HashMap<>(16);
        Map<String, String> mapKeynote4 = new HashMap<>(16);
        Map<String, String> mapRemark = new HashMap<>(16);

        map.put("touser",openId);

        mapFirst.put("value",wxTemplate.getFirst());
        mapFirst.put("color","#173177");

        mapKeynote1.put("value",wxTemplate.getKeyNote());
        mapKeynote1.put("color","#173177");

        mapKeynote2.put("value", DateUtils.timeStampToStr(menses.getLastMensesTime()));
        mapKeynote2.put("color","#173177");

        mapKeynote3.put("value",DateUtils.timeStampToStr(menses.getMensesTime()));
        mapKeynote3.put("color","#173177");

        mapKeynote4.put("value",day+"天\n");
        mapKeynote4.put("color","#173177");

        mapRemark.put("value",notice);
        mapRemark.put("color","#173177");

        mapData.put("first", JSON.parseObject(JSON.toJSONString(mapFirst)));
        mapData.put("keyword1", JSON.parseObject(JSON.toJSONString(mapKeynote1)));
        mapData.put("keyword2", JSON.parseObject(JSON.toJSONString(mapKeynote2)));
        mapData.put("keyword3", JSON.parseObject(JSON.toJSONString(mapKeynote3)));
        mapData.put("keyword4", JSON.parseObject(JSON.toJSONString(mapKeynote4)));
        mapData.put("remark", JSON.parseObject(JSON.toJSONString(mapRemark)));

        map.put("template_id",wxTemplate.getWxTemplateId());
        //map.put("url","http://www.baidu.com");
        map.put("data", JSON.parseObject(JSON.toJSONString(mapData)));
        return JSON.toJSONString(map);
    }
}
