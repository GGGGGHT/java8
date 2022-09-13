package com.ggggght.learningjava8.thread;

import java.util.List;

import sun.jvm.hotspot.tools.Tool;

public class PrintThreadIds extends Tool {
    public static void main(String[] args) {
        PrintThreadIds tool = new PrintThreadIds();
        tool.start(args);
        tool.stop();
    }

    @Override
    public void run() {
        List<ThreadInfo> infos = ThreadUtils.getAllThreadInfos();
        for (ThreadInfo info : infos) {
            System.out.printf("Thread@%s: tid=%d nid=0x%x\n",
                info.getAddress(), info.getTid(), info.getNid());
        }
    }
}