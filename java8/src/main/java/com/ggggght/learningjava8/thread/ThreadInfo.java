package com.ggggght.learningjava8.thread;

import lombok.Data;
import sun.jvm.hotspot.debugger.Address;

@Data(staticConstructor="of")
public class ThreadInfo {
    private final Address address;
    private final long tid;
    private final long nid;
}