package com.ggggght.learningjava8.springboot;

import cn.hutool.core.annotation.Alias;
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
// @Import(ServerImportSelector.class)
@Import(ServerImportBeanDefinitionRegistrar.class)
public @interface EnableServer {

	// @Alias("type")
	// Server.Type value();

	@Alias("value")
	Server.Type type();

}
