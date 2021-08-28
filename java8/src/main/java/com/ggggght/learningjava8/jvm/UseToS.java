package com.ggggght.learningjava8.jvm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ToS 栈顶缓存(Top-of-Stack Cashing)简称ToS技术,主要关注对频繁访问栈顶元素操作的性能优化.
 * 在一个典型的计算机系统各级存储结构中,处理器从突破器中读取数据比从主存储器(内存)中读取要快上百倍.
 * ToS通过将频繁访问的栈顶元素缓存在CPU硬件寄存器中,能够大幅度减少内存访问次数,达到提高性能的目的.
 *
 */
@SuppressWarnings("all")
public class UseToS {
  private static final Logger LOGGER = LoggerFactory.getLogger(UseToS.class);
}
