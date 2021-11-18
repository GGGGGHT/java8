// package com.ggggght.learningjava8.jvm;
//
// import java.util.Objects;
// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
// import sun.jvm.hotspot.runtime.Arguments;
//
// @SuppressWarnings("all")
// public class Argc {
//   private static final Logger LOGGER = LoggerFactory.getLogger(Argc.class);
//
//   private void printVMFlags() {
//     final String jvmFlags = Arguments.getJVMFlags();
//     if (Objects.nonNull(jvmFlags)) {
//       System.out.println("jvmFlags = " + jvmFlags);
//     }
//
//     final String jvmArgs = Arguments.getJVMArgs();
//     if (Objects.nonNull(jvmArgs)) {
//       System.out.println("jvmArgs = " + jvmArgs);
//     }
//     final String javaCommand = Arguments.getJavaCommand();
//     if (Objects.nonNull(javaCommand)) {
//       System.out.println("javaCommand = " + javaCommand);
//     }
//   }
//
//   public static void main(String[] args) {
//     new Argc().printVMFlags();
//   }
// }
