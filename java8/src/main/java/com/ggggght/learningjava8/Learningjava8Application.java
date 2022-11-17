package com.ggggght.learningjava8;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.security.servlet.ManagementWebSecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.ApplicationContext;
// import org.springframework.data.redis.core.StringRedisTemplate;

import javax.management.*;
import java.io.IOException;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@EnableWebSecurity
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class, ManagementWebSecurityAutoConfiguration.class})
//@MapperScan(basePackages = "com.ggggght.learningjava8")
public class Learningjava8Application implements ApplicationRunner {

	@Autowired
	ApplicationContext context;

	@Value("${test.value}")
	Integer value;

	@Value("${test.value2}")
	String value2;

	// @Autowired
	// StringRedisTemplate template;

	public static void main(String[] args) throws IOException, MalformedObjectNameException,
			NotCompliantMBeanException, InstanceAlreadyExistsException, MBeanRegistrationException {
		// MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();

		// Construct the ObjectName for the Hello MBean we will register
		// ObjectName mbeanName = new ObjectName("com.example:type=Hello");

		// Create the Hello World MBean
		// Hello mbean = new Hello();

		// Register the Hello World MBean
		// mbs.registerMBean(mbean, mbeanName);

		// MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
		// mbs.registerMBean(new Hello(),new ObjectName( "com.ggggght:type=Hello"));
		SpringApplication.run(Learningjava8Application.class, args);
		// System.out.println("Learningjava8Application.main");
		// System.out.println("hello world");
	}

	public static void main1(String[] args) throws RuntimeException {

	}
	@Override
	public void run(ApplicationArguments args) throws Exception {
		// String[] beans = context.getBeanDefinitionNames();
		// for (String bean : beans) {
		// System.out.println(bean);
		// }
		// System.out.println(value);
		// System.out.println(value2);
		// final List<String> list = List.of("1", "2", "3", "4");
		// String[] arr = list.stream().map(String::valueOf).toArray(String[]::new);
		// System.out.println("template.opsForSet().isMember(\"KEY\", 4) = " +
		// template.opsForSet().isMember("KEY", "4"));
		// System.out.println("template.opsForSet().isMember(\"KEY\", 5) = " +
		// template.opsForSet().isMember("KEY", "5"));
	}

}
