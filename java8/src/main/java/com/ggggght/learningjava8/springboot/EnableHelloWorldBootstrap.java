package com.ggggght.learningjava8.springboot;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;

/**
 * @desc: com.ggggght.learningjava8.springboot
 * @date: 2021/4/23 21:21
 * @author: ggggght
 */
@Configuration
// @EnableHelloWorld
@EnableServer(type = Server.Type.HTTP)
public class EnableHelloWorldBootstrap {
	public static void main(String[] args) {
		final AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.register(EnableHelloWorldBootstrap.class);
		context.refresh();
		// final String helloWorld = context.getBean("helloWorld", String.class);
		// System.out.printf("helloWorld = %s \n", helloWorld);
		final Server server = context.getBean(Server.class);
		// System.out.println(server);
		server.start();

		server.stop();
		context.close();
	}
}
