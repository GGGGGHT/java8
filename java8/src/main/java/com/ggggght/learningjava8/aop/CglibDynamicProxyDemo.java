package com.ggggght.learningjava8.aop;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;

/**
 * CGLIB 动态代理
 */
public class CglibDynamicProxyDemo {
    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        // set supper class
        enhancer.setSuperclass(DefaultEchoServiceImpl.class);
        // set interface
        enhancer.setInterfaces(new Class[] {EchoService.class});
        enhancer.setCallback((MethodInterceptor) (source, method, args1, methodProxy) -> {
            long startTime = System.currentTimeMillis();
            Object result = methodProxy.invokeSuper(source, args1);
            long costTime = System.currentTimeMillis() - startTime;
            System.out.printf("[CGLIB] costTime: %d%n", costTime);
            return result;
        });

        EchoService echoService = (EchoService) enhancer.create();
        System.out.println(echoService.echo("Hello World"));
    }
}
