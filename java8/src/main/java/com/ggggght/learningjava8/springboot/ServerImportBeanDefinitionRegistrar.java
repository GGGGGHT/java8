package com.ggggght.learningjava8.springboot;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

import java.util.stream.Stream;

/**
 * @desc: com.ggggght.learningjava8.springboot
 * @date: 2021/4/23 21:57
 * @author: ggggght
 */
public class ServerImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {

	@Override
	public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
		final ServerImportSelector importSelector = new ServerImportSelector();
		final String[] selectedClassNames = importSelector.selectImports(importingClassMetadata);
		Stream.of(selectedClassNames).map(BeanDefinitionBuilder::genericBeanDefinition)
				.map(BeanDefinitionBuilder::getBeanDefinition).forEach(beanDefinition -> {
					BeanDefinitionReaderUtils.registerWithGeneratedName(beanDefinition, registry);
				});
	}

}
