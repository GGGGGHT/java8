package com.ggggght.learningjava8.controller;

import com.ggggght.learningjava8.jmx.HelloMBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.management.*;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.net.MalformedURLException;


/**
 * @desc: com.ggggght.learningjava8.controller
 * @date: 2021/3/27 14:09
 * @author: ggggght
 */
@RestController
public class TestController {
	// static HelloMBean mbeanProxy;
	//
	// static {
	// 	JMXServiceURL url =
	// 			null;
	// 	try {
	// 		url = new JMXServiceURL("service:jmx:rmi:///jndi/rmi://:1099/jmxrmi");
	// 	} catch (MalformedURLException e) {qw
	// 		e.printStackTrace();
	// 	}
	// 	JMXConnector jmxc = null;
	// 	try {
	// 		jmxc = JMXConnectorFactory.connect(url, null);
	// 	} catch (IOException e) {
	// 		e.printStackTrace();
	// 	}
	// 	MBeanServerConnection mbsc = null;
	// 	try {
	// 		mbsc = jmxc.getMBeanServerConnection();
	// 	} catch (IOException e) {
	// 		e.printStackTrace();
	// 	}
	// 	ObjectName mbeanName = null;
	// 	try {
	// 		mbeanName = new ObjectName("com.example:type=Hello");
	// 	} catch (MalformedObjectNameException e) {
	// 		e.printStackTrace();
	// 	}
	// 	mbeanProxy =
	// 			JMX.newMBeanProxy(mbsc, mbeanName, HelloMBean.class, true);
	// }
	//
	// @GetMapping("/hello")
	// public String sayHello() {
	// 	return "hello world!";
	// }
	
	@Scheduled(cron = "* * * * * *")
	@GetMapping("/do")
	public void doSomeThing() {
		// System.out.println(mbeanProxy.getCacheSize());
	}
	
	public static void main(String[] args) throws InterruptedException {
		int[] ar = new int[0];
		final TestController obn = new TestController();
		TestController[] arr = new TestController[0];
		Thread.sleep(1000000L);
	}
}
