package com.ggggght.learningjava8.Time;

import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.OffsetDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.Test;

import static java.time.DayOfWeek.MONDAY;
import static java.time.temporal.ChronoField.DAY_OF_WEEK;
import static java.time.temporal.ChronoField.MICRO_OF_SECOND;
import static java.time.temporal.ChronoField.MONTH_OF_YEAR;
import static java.time.temporal.TemporalAdjusters.lastDayOfMonth;
import static java.time.temporal.TemporalAdjusters.nextOrSame;

/**
 * @author ght
 * @Desc
 * @date 2019-12-26 12:23 PM jdk8 之前用来处理时间的类或方法都不是线程安全的 jdk8 之后的java.time包下的这些类都是线程安全的
 * java.time.chrono java.time.format java.time.temporal java.time.zone
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
	 * @target LocalDateTime测试 LocalDateTime 与 LocalDate LocalTime 用法一样 都是人类可读的形式
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
	 * @target Instant测试 Instant 时间戳(以 unix元年开始 从1970年1月1日 00:00:00秒开始 到目前为止的毫秒值)
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
		}
		catch (InterruptedException e) {
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
		}
		catch (InterruptedException e) {
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
	 * @target Period 用来计算两个日期之间的间隔
	 * @bug 当两个时间跨度超过一年后 getMonth的返回值有错误
	 * @fix ChronoUnit.MONTHS.between 使用这个函数来获得两个时间之间的月份差
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

		long month = ChronoUnit.MONTHS.between(date1, date2);
		System.out.println(date1 + "与" + date2 + "的间隔的月数为" + month + "月");
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

	@Test
	public void getTest() {
		System.out.println(LocalDateTime.now().get(MICRO_OF_SECOND));
	}

	@Test
	public void test() {
		LocalDate date = LocalDate.of(2014, 3, 18);
		date = date.with(MONTH_OF_YEAR, 9); // 2014-09-18
		System.out.println(date);
		date = date.plusYears(2).minusDays(10); // 2016-09-j08
		date.withYear(2011);
		System.out.println(date);
	}

	@Test
	public void withAttributeTest() {
		LocalDate date1 = LocalDate.now(); // 2021-06-08
		LocalDate date2 = date1.withYear(2011); // 2011-06-08
		LocalDate date3 = date2.withDayOfMonth(25); // 2011-06-25
		LocalDate date4 = date3.with(MONTH_OF_YEAR, 9);
		System.out.println(date4);
	}

	@Test
	public void temporalAdjustersTest() {
		LocalDate date1 = LocalDate.now(); // 2021-06-28
		LocalDate date2 = date1.with(nextOrSame(DayOfWeek.SUNDAY));// 2021-06-13
		LocalDate date3 = date2.with(lastDayOfMonth()); // 06-30
		System.out.println("date1 = " + date1);
		System.out.println("date2 = " + date2);
		System.out.println("date3 = " + date3);
	}

	@Test
	public void useNextWorkingDay() {
		LocalDate now = LocalDate.of(2021, 06, 13);
		LocalDate nextDay = now.with(t -> new NextWorkingDay().adjustInto(t));
		System.out.println("now = " + now);
		System.out.println("nextDay = " + nextDay);

		now.with(temporal -> {
			DayOfWeek dow = DayOfWeek.of(temporal.get(DAY_OF_WEEK));
			int dayToAdd = 1;
			if (dow == DayOfWeek.FRIDAY) {
				dayToAdd = 3;
			}
			else if (dow == DayOfWeek.SATURDAY) {
				dayToAdd = 2;
			}

			return temporal.plus(dayToAdd, ChronoUnit.DAYS);
		});
	}

	/**
	 * 实现一个定制的TemporalAdjuster请设计一个NextWorkingDay类，
	 * 该类实现了TemporalAdjuster接口，能够计算明天的日期，同时过滤掉周六和周日这些节假日。 格式如下所示： <pre>
	 *      date = date.with(new NextWorkingDay())
	 *  </pre> 如果当天的星期介于周一至周五之间，日期向后移动一天；如果当天是周六或者周日，则返回下一个周一。
	 */
	class NextWorkingDay implements TemporalAdjuster {

		@Override
		public Temporal adjustInto(Temporal temporal) {
			int day = temporal.get(DAY_OF_WEEK);
			if (day >= 1 && day < 5) {
				return temporal.plus(1, ChronoUnit.DAYS);
			}

			if (day == 5) {
				return temporal.plus(3, ChronoUnit.DAYS);
			}

			return temporal.with(nextOrSame(MONDAY));
		}

	}

	@Test
	public void formatTest() {
		LocalDate date = LocalDate.of(2021, 3, 18);
		String s1 = date.format(DateTimeFormatter.BASIC_ISO_DATE);
		System.out.println("s1 = " + s1);
		String s2 = date.format(DateTimeFormatter.ISO_LOCAL_DATE);
		System.out.println("s2 = " + s2);
		LocalDate date1 = LocalDate.parse("20210318", DateTimeFormatter.BASIC_ISO_DATE);
		LocalDate date2 = LocalDate.parse("2021-03-18", DateTimeFormatter.ISO_LOCAL_DATE);

		System.out.println("date1.equals(date2) = " + date1.equals(date2));

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate date3 = LocalDate.of(2014, 3, 18);
		String formattedDate = date3.format(formatter);
		System.out.println("formattedDate = " + formattedDate);
		LocalDate date4 = LocalDate.parse(formattedDate, formatter);
		System.out.println("date4 = " + date4);
	}

}
