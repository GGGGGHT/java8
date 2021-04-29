package com.ggggght.learningjava8.springboot;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @desc: com.ggggght.learningjava8.springboot
 * @date: 2021/4/23 21:20
 * @author: ggggght
 */
@Configuration
public class HelloWorldConfiguration {
	@Bean
	public String helloWorld() {
		return "hello world!";
	}
}
