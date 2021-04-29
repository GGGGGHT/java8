package com.ggggght.learningjava8.springboot;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @desc: com.ggggght.learningjava8.springboot
 * @date: 2021/4/23 21:17
 * @author: ggggght
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(HelloWorldConfiguration.class)
public @interface EnableHelloWorld {

}
