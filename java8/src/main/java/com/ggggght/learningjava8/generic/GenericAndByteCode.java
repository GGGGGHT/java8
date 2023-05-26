package com.ggggght.learningjava8.generic;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * 泛型与字节码
 */
public class GenericAndByteCode {

    /**
     * 0 aload_1 #load pair
     * 1 invokevirtual #7 <com/ggggght/learningjava8/generic/Pair.left : ()Ljava/lang/Object;>
     * 4 checkcast #13 <java/lang/String>
     * 7 astore_2
     * 8 return
     * @param pair
     */
    public void foo(Pair<String> pair) {
        // Generic array creation
        // Pair<String>[] p = new Pair<String>[3];
        String left = pair.left();
    }

    /**
     * overload function
     * print(List<String>)' clashes with 'print(List<Integer>)'; both methods have same erasure
     */
    // public void print(List<String> list) {}
    // public void print(List<Integer> list) {}
    public static void main(String[] args) {
        Type type1 = getGenericRuntimeType(new Wrapper<List<String>>());
        Type type2 = getGenericRuntimeType(new Wrapper<List<String>>() {});
        Type type3 = getGenericRuntimeType(new Wrapper<Integer>() {});

        System.out.println(type1);
        System.out.println(type2);
        System.out.println(type3);
    }

    public static <T> Type getGenericRuntimeType(Wrapper<T> wrapper) {
        Type type = wrapper.getClass().getGenericSuperclass();
        if (type == null) return null;

        return type instanceof ParameterizedType parameterizedType ?
            parameterizedType.getActualTypeArguments()[0] :
            null;
    }
}
class Wrapper<T> {}


/**
 * 定义一个泛型类，其中
 * Type Parameter是T extends Number
 * Type Variable是T
 * Type Argument是Foo<Integer>里的Integer
 */
class Foo<T extends Number> {}

record Pair<T>(T left, T right) {
}


