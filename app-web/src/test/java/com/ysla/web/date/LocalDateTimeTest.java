package com.ysla.web.date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.security.SecureRandom;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Set;

/**
 * java8的时间日期api
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class LocalDateTimeTest {

    /**
     * zonedDate zonedTime zonedDateTime
     */
    @Test
    public void test6(){
        Set<String> ids = ZoneId.getAvailableZoneIds();
        //ids.stream().forEach(System.out::println);

        LocalDateTime localDateTime = LocalDateTime.now(ZoneId.of("US/Pacific"));
        System.out.println(localDateTime);

        LocalDateTime ldt1 = LocalDateTime.now();
        ZonedDateTime zdt = ldt1.atZone(ZoneId.of("US/Pacific"));
        System.out.println(zdt);
    }

    /**
     * dateTimeFormatter 格式日期/时间
     */
    @Test
    public void test5(){
        DateTimeFormatter dtf = DateTimeFormatter.ISO_DATE_TIME;
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localDateTime.format(dtf));

        DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH:mm:ss");
        String str = localDateTime.format(dtf2);
        System.out.println(str);

        LocalDateTime newDate = localDateTime.parse(str,dtf2);
        System.out.println(newDate);
    }

    /**
     * temporalAdjuster: 时间校正器
     */
    @Test
    public void test4(){
        LocalDateTime ldt1 = LocalDateTime.now();
        System.out.println(ldt1.withDayOfMonth(10));
        System.out.println(ldt1.with(TemporalAdjusters.next(DayOfWeek.FRIDAY)));

        // 自定义 下一个工作日
        LocalDateTime localDateTime = ldt1.with(temporal -> {
            LocalDateTime ldt = (LocalDateTime) temporal;
            DayOfWeek dayOfWeek = ldt.getDayOfWeek();
            if (dayOfWeek.equals(DayOfWeek.FRIDAY)){
                return ldt.plusDays(3);
            } else if (dayOfWeek.equals(DayOfWeek.SATURDAY)){
                return ldt.plusDays(2);
            } else {
                return ldt.plusDays(1);
            }
        });
        System.out.println(localDateTime);
    }

    /**
     * duration 计算两个时间之间的间隔
     * period 计算两个日期之间的间隔
     */
    @Test
    public void test3(){
        Instant ins1 = Instant.now();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Instant ins2 = Instant.now();
        Duration duration = Duration.between(ins1,ins2);
        System.out.println(duration.toMillis());


        LocalTime lt1 = LocalTime.now();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        LocalTime lt2 = LocalTime.now();
        System.out.println(Duration.between(lt1,lt2).toMillis());

        LocalDate ld1 = LocalDate.of(2018,12,12);
        LocalDate ld2 = LocalDate.now();
        Period period = Period.between(ld1,ld2);
        System.out.println(period.getYears());
        System.out.println(period.getMonths());
        System.out.println(period.getDays());

    }

    /**
     * instant 时间戳
     */
    @Test
    public void instantTest(){
        // 默认获取utc时区时间
        Instant instant = Instant.now();
        System.out.println(instant);

        // 设置+8时区
        OffsetDateTime offsetDateTime = instant.atOffset(ZoneOffset.ofHours(8));
        System.out.println(offsetDateTime);

        // 获取毫秒值
        System.out.println(instant.toEpochMilli());
    }

    /**
     * localDateTime localDate localTime
     */
    @Test
    public void localDateTime1(){
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localDateTime);

        LocalDateTime localDateTime1 = LocalDateTime.of(2018,8,8,12,32,10);
        System.out.println(localDateTime1);

        LocalDateTime ldt1 = localDateTime.plusYears(1);
        System.out.println(ldt1);

        LocalDateTime ldt2 = localDateTime.minusYears(1);
        System.out.println(ldt2);

        System.out.println(ldt1.getYear());
        System.out.println(ldt1.getMonth().getValue());
        System.out.println(ldt1.getDayOfMonth());
        System.out.println(ldt1.getHour());
        System.out.println(ldt1.getMinute());

        LocalDate localDate = LocalDate.now();
        LocalDateTime now = LocalDateTime.of(localDate.getYear(),
                localDate.getMonth().getValue(),
                localDate.getDayOfMonth(),22,0,0).plusDays(27);
        System.out.println(now.toEpochSecond(ZoneOffset.ofHours(8)));

    }

}
