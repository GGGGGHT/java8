// package com.ggggght.learningjava8.jmx;
//
// import java.io.FileNotFoundException;
// import java.io.IOException;
//
// import sun.jvm.hotspot.oops.ObjectHeap;
// import sun.jvm.hotspot.runtime.VM;
// import sun.jvm.hotspot.tools.Tool;
//
// /**
// * 程序示例：利用SA探测运行时常量池
// *
// * @author Chen Tao
// *
// */
// public class MyWatchConstantPool extends Tool {
//
// /**
// *
// * @param args - pid
// * @throws FileNotFoundException
// */
// public static void main(String[] args) throws FileNotFoundException {
// MyWatchConstantPool test = new MyWatchConstantPool();
// test.start(new String[] {"5360"});
// test.stop();
// }
//
// @Override
// public void run() {
// try {
// watchConstantPool();
// } catch (IOException e) {
// e.printStackTrace();
// }
// }
//
// public void watchConstantPool() throws IOException {
// ObjectHeap heap = VM.getVM().getObjectHeap();
// try {
// heap.iterate(new HeapConstantPoolVisitor());
// } catch (RuntimeException re) {
// System.out.println(re);
// }
// }
// }