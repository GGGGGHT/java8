package com.ggggght.learningjava8.reflect;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

/**
 * 学习反射相关
 */
public class MethodReflect {
    public void print(String s) {
        System.out.println("hello: " + s);
    }
    public static void main(String[] args) throws Throwable {
        Runnable r = () -> System.out.println("hello");

        System.out.println(r.getClass().getName());
        MethodHandle methodHandle =
            MethodHandles.lookup().findVirtual(MethodReflect.class, "print", MethodType.methodType(void.class, String.class));
        MethodReflect methodReflect = new MethodReflect();
        methodHandle.invoke(methodReflect, "world");
    }
}
