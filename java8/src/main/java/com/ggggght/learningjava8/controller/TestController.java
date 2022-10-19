package com.ggggght.learningjava8.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.availability.AvailabilityChangeEvent;
import org.springframework.boot.availability.LivenessState;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @desc: com.ggggght.learningjava8.controller
 * @date: 2021/3/27 14:09
 * @author: ggggght
 */
@RestController
@RequiredArgsConstructor
public class TestController {

    /**
     * add servlet filter
     *
     * @return Filter
     */

    // static HelloMBean mbeanProxy;
    //
    // static {
    // JMXServiceURL url =
    // null;
    // try {
    // url = new JMXServiceURL("service:jmx:rmi:///jndi/rmi://:1099/jmxrmi");
    // } catch (MalformedURLException e) {qw
    // e.printStackTrace();
    // }
    // JMXConnector jmxc = null;
    // try {
    // jmxc = JMXConnectorFactory.connect(url, null);
    // } catch (IOException e) {
    // e.printStackTrace();
    // }
    // MBeanServerConnection mbsc = null;
    // try {
    // mbsc = jmxc.getMBeanServerConnection();
    // } catch (IOException e) {
    // e.printStackTrace();
    // }
    // ObjectName mbeanName = null;
    // try {
    // mbeanName = new ObjectName("com.example:type=Hello");
    // } catch (MalformedObjectNameException e) {
    // e.printStackTrace();
    // }
    // mbeanProxy =
    // JMX.newMBeanProxy(mbsc, mbeanName, HelloMBean.class, true);
    // }
    //
    int i = 0;

    public static void main(String[] args) throws InterruptedException {
        int[] ar = new int[0];
        // final TestController obn = new TestController();
        TestController[] arr = new TestController[0];
        Thread.sleep(1000000L);
    }

    @GetMapping("/hello")
    public String sayHello() {
        i++;
        if (i % 5 == 0) {
            throw new IllegalArgumentException("test throw exception");
        }

        return "hello world!";
    }

    @Scheduled(cron = "* * * * * *")
    @GetMapping("/do")
    public void doSomeThing() {
        // System.out.println(mbeanProxy.getCacheSize());
    }

    private final ApplicationContext context;

    @GetMapping("/down")
    void down() {
        AvailabilityChangeEvent.publish(this.context, LivenessState.BROKEN);
    }

    @Bean
    ApplicationListener<AvailabilityChangeEvent<?>> availabilityChangeEventApplicationListener() {
        return event -> System.out.println(event.getResolvableType() + ":" + event.getState());
    }
}
