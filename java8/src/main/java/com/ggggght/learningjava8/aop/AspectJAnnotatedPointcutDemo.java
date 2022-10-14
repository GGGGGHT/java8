package com.ggggght.learningjava8.aop;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
public class AspectJAnnotatedPointcutDemo {
    public static void main(String[] args) {
        var context = new AnnotationConfigApplicationContext();
        context.register(AspectJAnnotatedPointcutDemo.class, AspectConfiguration.class);
        context.refresh();

        var demoBean = context.getBean(AspectJAnnotatedPointcutDemo.class);
        demoBean.execute();
        context.close();
    }

    public void execute() {
        System.out.println("AspectJAnnotatedPointcutDemo#execute");
    }
}
