package com.ggggght.learningjava8.thread;

import java.util.ArrayList;
import java.util.List;

import sun.jvm.hotspot.debugger.Address;
import sun.jvm.hotspot.oops.Field;
import sun.jvm.hotspot.oops.InstanceKlass;
import sun.jvm.hotspot.oops.Klass;
import sun.jvm.hotspot.oops.LongField;
import sun.jvm.hotspot.oops.Oop;
import sun.jvm.hotspot.runtime.JavaThread;
import sun.jvm.hotspot.runtime.Threads;
import sun.jvm.hotspot.runtime.VM;

public class ThreadUtils {
    public static List<ThreadInfo> getAllThreadInfos() {
        List<ThreadInfo> infos = new ArrayList<ThreadInfo>();
        Threads threads = VM.getVM().getThreads();
        for (JavaThread thread = threads.first(); thread != null; thread = thread.next()) {
            Address address = thread.getAddress();
            long tid = getTid(thread);
            long nid = Long.parseLong(thread.getThreadProxy().toString());
            infos.add(ThreadInfo.of(address, tid, nid));
        }

        return infos;
    }
    public static long getTid(JavaThread thread) {
        final long BAD_TID = -1L;
        
        Oop threadObj = thread.getThreadObj();
        Klass klass = threadObj.getKlass();
        if (!(klass instanceof InstanceKlass)) return BAD_TID;
        
        InstanceKlass instanceKlass = (InstanceKlass) klass;
        Field tidField = instanceKlass.findField("tid", TypeCode.LONG);
        if (!(tidField instanceof LongField)) return BAD_TID;
        
        long tid = ((LongField) tidField).getValue(threadObj);
        return tid;

    }
}