package com.ggggght.learningjava8.Time;

import org.apache.commons.lang3.time.FastDateFormat;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.TemporalUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author ght
 * @Desc
 * @date 2019-12-26 12:23 PM
 * jdk8 之前用来处理时间的类或方法都不是线程安全的
 * jdk8 之后的java.time包下的这些类都是线程安全的
 * java.time.chrono
 * java.time.format
 * java.time.temporal
 * java.time.zone
 */

@SuppressWarnings("all")
public class LearnTime {
    /**
     * @target 不安全的时间格式化 会抛异常
     */
    @Test
    public void timeFormatUnsafeTest() throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

        Callable<Date> task = new Callable<Date>() {
            @Override
            public Date call() throws Exception {
                return sdf.parse("20191226");
            }
        };
        ExecutorService pool = Executors.newFixedThreadPool(10);

        List<Future<Date>> results = new ArrayList<>(10);

        for (int i = 0; i < 10; i++) {
            results.add(pool.submit(task));
        }

        for (Future<Date> result : results) {
            System.out.println(result.get());
        }
    }

    /**
     * @target 安全的时间格式化
     */
    @Test
    public void timeFormatSafeTest() throws ExecutionException, InterruptedException {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd");

        Callable<LocalDate> task = new Callable<LocalDate>() {
            @Override
            public LocalDate call() throws Exception {
                return LocalDate.parse("20191226", dtf);
            }
        };

        ExecutorService pool = Executors.newFixedThreadPool(10);

        List<Future<LocalDate>> results = new ArrayList<>(10);

        for (int i = 0; i < 10; i++) {
            results.add(pool.submit(task));
        }

        for (Future<LocalDate> result : results) {
            System.out.println(result.get());
        }
    }

    /**
     * @target LocalDateTime测试
     * LocalDateTime 与 LocalDate  LocalTime 用法一样 都是人类可读的形式
     */
    @Test
    public void localDateTest() {
        // 获取当前时间
        LocalDateTime ldt = LocalDateTime.now();
        System.out.println(ldt);

        // 可以获取指定时间的对象
        LocalDateTime ldt2 = LocalDateTime.of(2019, 12, 20, 10, 50, 40);
        System.out.println(ldt2);

        // 可以通过plusXXX()来加年/月/日/时/分/秒等 使用minusXXX()来减年/月/日/时/分/秒等
        LocalDateTime ldt3 = ldt.plusYears(1L);
        System.out.println(ldt3);
        LocalDateTime ldt4 = ldt.minusYears(1l);
        System.out.println(ldt4);

        // 使用getXXX()或getXXXValue() 来获取相应的对象或值
        Month month = ldt.getMonth();
        System.out.println(month);
        int monthValue = ldt.getMonthValue();
        System.out.println(monthValue);

    }

    /**
     * @target Instant测试
     * Instant 时间戳(以 unix元年开始 从1970年1月1日 00:00:00秒开始 到目前为止的毫秒值)
     */
    @Test
    public void instantTest() {
        // 默认获取的是UTC时间
        Instant utc = Instant.now();
        System.out.println(utc);

        // 获取当前的北京时间
        OffsetDateTime cn = utc.atOffset(ZoneOffset.ofHours(8));
        System.out.println(cn);

        // 获取当前的时间戳
        long timestamp = utc.toEpochMilli();
        System.out.println(timestamp);
    }

    /**
     * @target Duration 用来计算两个时间之间的间隔
     */
    @Test
    public void durationTest() {
        Instant start = Instant.now();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Instant end = Instant.now();
        long seconds = Duration.between(start, end).getSeconds();
        // 获取毫秒数是toMillis() 而不是getMillis()
        long mills = Duration.between(start, end).toMillis();
        int nano = Duration.between(start, end).getNano();
        System.out.println("中间的间隔为: " + seconds + "秒");
        System.out.println("中间的间隔为: " + mills + "毫秒");
        System.out.println("中间的间隔为: " + nano + "纳秒");

        LocalDateTime startTime = LocalDateTime.now();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        LocalDateTime endTime = LocalDateTime.now();

        long timeSecond = Duration.between(startTime, endTime).getSeconds();
        long timeMills = Duration.between(startTime, endTime).toMillis();
        long timeNano = Duration.between(startTime, endTime).getNano();
        System.out.println("中间的间隔为: " + timeSecond + "秒");
        System.out.println("中间的间隔为: " + timeMills + "毫秒");
        System.out.println("中间的间隔为: " + timeNano + "纳秒");
    }

    /**
     * @target Period   用来计算两个日期之间的间隔
     */
    @Test
    public void periodTest() {
        LocalDate date1 = LocalDate.of(2019, 12, 25);
        LocalDate date2 = LocalDate.now();
        Period period = Period.between(date1, date2);
        int days = period.getDays();
        int years = period.getYears();
        int months = period.getMonths();
        System.out.println(date1 + "与" + date2 + "的间隔的年数为" + years + "年");
        System.out.println(date1 + "与" + date2 + "的间隔的月数为" + months + "月");
        System.out.println(date1 + "与" + date2 + "的间隔的天数为" + days + "天");
    }

    /**
     * @target TemporalAdjusters: 时间校正器
     */
    @Test
    public void temporalAdjusterTest() {
        LocalDateTime now = LocalDateTime.now();
        System.out.println(now);

        // 获取下一个周日的时间
        LocalDateTime nextSunDay = now.with(TemporalAdjusters.next(DayOfWeek.SUNDAY));
        System.out.println(nextSunDay);
    }

    /**
     * @target DateTimeFormatter
     */
    @Test
    public void dateTimeFormatterTest() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String stringNow = now.format(dtf);
        String format = dtf.format(now);
        System.out.println(stringNow);
        System.out.println(format);
        System.out.println();
        LocalDateTime newDate = now.parse(format, dtf);
        System.out.println(newDate);
    }

    /**
     * @target Date类型和LocalDateTime相互转换
     */
    @Test
    public void dateToLocalDateTime() {
        // 先获取当前时间
        Date date = new Date();
        // 获取当前的时区
        ZoneId zoneId = ZoneId.systemDefault();
        Instant instant = date.toInstant();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zoneId);
        System.out.println(localDateTime);

        LocalDateTime ldt = LocalDateTime.now();
        ZonedDateTime zdt = ldt.atZone(zoneId);
        Date date1 = Date.from(zdt.toInstant());
        System.out.println(date1);
    }

}
