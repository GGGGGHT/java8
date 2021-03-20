package com.ggggght.learningjava8.jmx;

import javax.management.MXBean;

/**
 * @author ggggght
 */
@MXBean
public interface HelloMBean {
    void sayHello();
    int add(int x, int y);

    String getName();

    int getCacheSize();
    void setCacheSize(int size);
}
