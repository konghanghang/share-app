package com.ysla.web.task;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;

/**
 * 关于第二个动态多任务.
 * 1.将动态定时任务的代码文件在项目中复制一份,改一下名
 * 2.在数据库插入多条数据,每个任务有一个查询数据库的sql语句,改为如下形式(根据id查询动态任务的cron语句)
 * Select("select cron from cron where cron_id= '1' limit 1")
 * 3.总结一下,就是在spring容器注册多个定时任务,每个定时任务的区别就是sql语句以及文件名,具体的任务任务代码不一样.
 * @author konghang
 */
@Configuration
@EnableScheduling
public class AtomicScheduleConfig implements SchedulingConfigurer {

    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
        scheduledTaskRegistrar.addTriggerTask(
                //1.添加任务内容(Runnable)
                () -> System.out.println("执行定时任务: " + LocalDateTime.now().toLocalTime()),
                //2.设置执行周期(Trigger)
                triggerContext -> {
                    //2.1 从数据库获取执行周期
                    String cron = "0 0/10 * * * ?";
                    //2.2 合法性校验.
                    if (StringUtils.isEmpty(cron)) {
                        // Omitted Code ..
                    }
                    //2.3 返回执行周期(Date)
                    return new CronTrigger(cron).nextExecutionTime(triggerContext);
                }
        );
    }
}
