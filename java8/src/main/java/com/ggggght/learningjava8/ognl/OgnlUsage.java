package com.ggggght.learningjava8.ognl;

/**
 * OGNL: Object Graph Navigation Language <br/>
 * <a href="https://commons.apache.org/proper/commons-ognl/language-guide.html">ongl</a>
 */
public class OgnlUsage {
    /**
     * 基本语法
     */
    void basicSyntax() {
        // 获取对象的属性 通过.来获取 e.g. user.name
        // 方法调用 通过()来调用 e.g. user.hashCode()
        // 数组访问 通过[]来访问 e.g. user[0]
        // OGNL 表达式都在当前对象的上下文中求值，链式调用使用上一个链接的结果作为下一个链接的当前对象.
        // name.toCharArray()[0].numericValue.toString()
        // 提取初始或根对象的名称属性（用户通过 OGNL 上下文提供给 OGNL）；
        // 对结果字符串调用 toCharArray() 方法；
        // 从结果数组中提取第一个字符（索引为 0 的字符）；
        // 从该字符获取 numericValue 属性（该字符表示为一个 Character 对象，并且 Character 类有一个名为 getNumericValue() 的方法）；
        // 在生成的 Integer 对象上调用 toString()。 该表达式的最终结果是最后一次调用 toString() 返回的字符串。
    }

    /**
     * 表达式
     */
    void expressions() {
        // 变量引用
        // #var = 99
        // ognl在this变量中提供了一个特殊的引用，它引用当前对象

        // 链式表达式
        // 如果在 . 后面使用括号表达式
    }

    void collectionConstruction() {

    }

    public static void main(String[] args) {

    }
}
