package com.ggggght.learningjava8;

import com.ggggght.learningjava8.generate.IdTestInnodb;
import com.ggggght.learningjava8.generate.IdTestInnodbDao;
import com.ggggght.learningjava8.jmx.Hello;
import com.sun.tools.javac.util.List;
import org.apache.ibatis.plugin.Intercepts;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.management.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.lang.reflect.AnnotatedElement;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;


@SpringBootApplication
@MapperScan(basePackages = "com.ggggght.learningjava8")
public class Learningjava8Application implements ApplicationRunner {
	@Autowired
	ApplicationContext context;
	@Value("${test.value}")
	Integer value;
	@Value("${test.value2}")
	String value2;
	@Autowired
	StringRedisTemplate template;

	public static void main(String[] args) throws IOException, DocumentException, MalformedObjectNameException, NotCompliantMBeanException, InstanceAlreadyExistsException, MBeanRegistrationException {
		MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
		mbs.registerMBean(new Hello(),new ObjectName( "com.ggggght:type=Hello"));
		SpringApplication.run(Learningjava8Application.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		// String[] beans = context.getBeanDefinitionNames();
		// for (String bean : beans) {
		//         System.out.println(bean);
		// }
		// System.out.println(value);
		// System.out.println(value2);
		// final List<String> list = List.of("1", "2", "3", "4");
		// String[] arr = list.stream().map(String::valueOf).toArray(String[]::new);
		// System.out.println("template.opsForSet().isMember(\"KEY\", 4) = " + template.opsForSet().isMember("KEY", "4"));
		// System.out.println("template.opsForSet().isMember(\"KEY\", 5) = " + template.opsForSet().isMember("KEY", "5"));
	}
}
