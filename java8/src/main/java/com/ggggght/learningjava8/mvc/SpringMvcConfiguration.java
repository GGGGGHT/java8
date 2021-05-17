package com.ggggght.learningjava8.mvc;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@Configurable
@ComponentScan(basePackageClasses = SpringMvcConfiguration.class)
public class SpringMvcConfiguration {
}
