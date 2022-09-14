package com.ggggght.learningjava8.aop;

import java.lang.reflect.Method;
import org.springframework.util.ReflectionUtils;

/**
 * use <b>-Dsun.misc.ProxyGenerator.saveGeneratedFiles=true</b> to generate class file
 */
public class TargetFilterDemo {
    public static void main(String[] args) throws ClassNotFoundException {
        String targetClassName = "com.ggggght.learningjava8.Learningjava8Application";
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        Class<?> targetClass = classLoader.loadClass(targetClassName);
        Method method = ReflectionUtils.findMethod(targetClass, "main1", String[].class);
        System.out.println(method);

        ReflectionUtils.doWithMethods(targetClass,
            method1 -> System.out.println("method: " + method1.getName() ), method12 -> {
                Class<?>[] exceptionTypes = method12.getExceptionTypes();
                return exceptionTypes.length == 1 && NullPointerException.class.equals(exceptionTypes[0]);
            });
    }
}
