package com.ggggght.learningjava8.autowired;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.AbstractEnvironment;

@Configuration
@ComponentScan(basePackages = "com.ggggght.learningjava8.autowired")
public class CalculatingServiceBootstrap {
	static {
		System.setProperty(AbstractEnvironment.ACTIVE_PROFILES_PROPERTY_NAME, "Java8");
		System.setProperty(AbstractEnvironment.DEFAULT_PROFILES_PROPERTY_NAME, "Java7");
	}

	public static void main(String[] args) {
		AnnotationConfigApplicationContext app = new AnnotationConfigApplicationContext();
		app.register(CalculatingServiceBootstrap.class);
		app.refresh();

		CalculatingService bean = app.getBean(CalculatingService.class);
		bean.sum(1, 2, 3, 4, 5);

		app.close();
	}
}
