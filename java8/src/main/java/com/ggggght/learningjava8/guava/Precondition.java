package com.ggggght.learningjava8.guava;

import static com.google.common.base.Preconditions.*;

import org.junit.Test;
/**
 * @Dest: 使用guava进行数据验证
 */
@SuppressWarnings("all")
public class Precondition {

    /**
     * @desc: 每个方法都有3个变体
     *  - 没有额外的参数 抛出异常时没有错误信息
     *  - 一个object类型参数 抛出错误信息时用object.toString()做为异常信息
     *  - 一个额外的String参数 可以带有任意数量的其他object参数 类似于printf 使用%s做为替换符
     * @method: checkArgument(表达式,[错误信息模板],[变量])
     * @method: checkNotNull(变量引用，[错误信息模板]，[变量])
     * @method: checkState(表达式,[错误信息模板],[变量])
     * @method: checkElementIndex(index,size) index: 要检验的下标位置， size: 容器的大小
     */
    @Test
    public void preconditionTest() {
        int i = -1;
        int j = -2;
        checkArgument(i + j < 0);
        checkArgument(i >= 0, "Argument was %s but expected nonnegative", i);
        checkArgument(i < j, "Expected i < j, but %s >= %s", i, j);
        checkNotNull(null, "Object not allow,but it was null");
        checkState(1+1==2,"never error");
        int[] arr = new int[10];
        checkElementIndex(11, arr.length);
    }
}
