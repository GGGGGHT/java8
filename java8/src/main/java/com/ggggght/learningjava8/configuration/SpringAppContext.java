package com.ggggght.learningjava8.configuration;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Map;
import java.util.Objects;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DeferredImportSelector;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

interface ServiceInterface {
    void test();
}

/**
 * 一般的，框架中如果基于AnnotationMetadata的参数实现动态加载类，
 * 一般会写一个额外的Enable注解，配合使用。
 */
@Documented @Target(ElementType.TYPE)
// @Import(ServiceImportSelector.class)
@Import(ServiceImportBeanDefinitionRegistrar.class)
@Retention(RetentionPolicy.RUNTIME)
@interface EnableService {
    String name();
}

public class SpringAppContext {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ConfigA.class);
        ServiceInterface bean = context.getBean(ServiceInterface.class);
        bean.test();
    }
}

class ServiceA implements ServiceInterface {

    @Override
    public void test() {
        System.out.println("ServiceA");
    }
}

class ServiceB implements ServiceInterface {

    @Override
    public void test() {
        System.out.println("ServiceB");
    }
}

class ServiceC implements ServiceInterface {

    private final String name;

    ServiceC(String name) {
        this.name = name;
    }

    @Override
    public void test() {
        System.out.println(name);
    }
}

// @Import的优先于本身的的类定义加载。
// 在Spring 4.2之后，@Import可以直接指定实体类，加载这个类定义到context中。
// e.g: 把上面代码中的ConfigA的@Import修改为@Import(ServiceB.class)，就会生成ServiceB的Bean到容器上下文中，之后运行main方法，输出为：ServiceB.证明@Import的优先于本身的的类定义加载.
// @Import(ServiceImportSelector.class)
@EnableService(name = "TestC") @Configuration class ConfigA {
    // @Bean
    // @ConditionalOnMissingBean
    public ServiceInterface getServiceA() {
        return new ServiceA();
    }
}

@Configuration class ConfigB {
    @Bean
    @ConditionalOnMissingBean
    public ServiceInterface getServiceB() {
        return new ServiceB();
    }
}

/**
 * 通过实现{@link ImportSelector}接口，可以实现动态加载类。 通过该接口的selectImports方法，可以返回一个类名的数组，这些类会被加载到容器上下文中。这个优先级高于@Import注解本身的类定义加载。<br/>
 * 通过实现{@link DeferredImportSelector}接口 返回的类都是最后加载的.
 */
class ServiceImportSelector implements DeferredImportSelector {

    /**
     * Select and return the names of which class(es) should be imported based on
     * the {@link AnnotationMetadata} of the importing @{@link Configuration} class.
     *
     * @param importingClassMetadata
     *
     * @return the class names, or an empty array if none
     */
    @Override
    public String @NotNull [] selectImports(@NotNull AnnotationMetadata importingClassMetadata) {
        // return new String[]{"com.ggggght.learningjava8.configuration.ConfigB"};
        Map<String, Object> map = importingClassMetadata.getAnnotationAttributes(EnableService.class.getName(), true);
        if (Objects.isNull(map)) {
            return new String[0];
        }

        String name = (String) map.get("name");
        if (Objects.equals(name, "B")) {
            return new String[] {"com.ggggght.learningjava8.configuration.ConfigB"};
        }
        return new String[0];
    }
}

class ServiceImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        Map<String, Object> map = importingClassMetadata.getAnnotationAttributes(EnableService.class.getName(), true);
        String name = (String) map.get("name");
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.rootBeanDefinition(ServiceC.class)
                                                                           .addConstructorArgValue(name);
        registry.registerBeanDefinition("serviceC", beanDefinitionBuilder.getBeanDefinition());
    }
}
