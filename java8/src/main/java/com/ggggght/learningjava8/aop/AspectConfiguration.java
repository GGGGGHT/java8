package com.ggggght.learningjava8.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class AspectConfiguration {
    @Pointcut("execution(public * * (..))")
    private void anyPublicMethod() {
    }

    @Before("anyPublicMethod()")
    public void beforeAnyPublicMethod() {
        System.out.println("@pointcut any public method.");
    }
}
