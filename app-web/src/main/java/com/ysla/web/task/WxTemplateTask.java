package com.ysla.web.task;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ysla.api.module.m.INoticeService;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * 微信模板消息定时任务
 * @author konghang
 */
@Configuration
@EnableScheduling
public class WxTemplateTask {

    @Reference(version = "${dubbo.service.version}",check = false, timeout = 10000)
    private INoticeService noticeService;

    /**
     * 每天21:00执行
     * oZPbCv_4HFKnOIncGW1NElOHc_UA
     * oZPbCv9szzusPj9PrREzaittN-x4 me
     */
    @Scheduled(cron = "0 0 21 * * ?")
    public void wxMensesTask(){
        noticeService.sendNotice("oZPbCv_4HFKnOIncGW1NElOHc_UA");
    }

}
