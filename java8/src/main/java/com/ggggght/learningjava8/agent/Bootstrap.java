// package com.ggggght.learningjava8.agent;
//
// import java.io.PrintStream;
// import java.lang.instrument.Instrumentation;
// import java.lang.instrument.UnmodifiableClassException;
//
// /**
// * <b> 增强 {@link com.ggggght.learningjava8.controller.TestController#sayHello} 方法 </b>
// */
// public class Bootstrap {
// private static final String JAR_NAME = "agent.jar";
// private static final String BOOTSTRAP = "com.ggggght.learningjava8.Bootstrap";
//
// private static PrintStream ps = System.err;
//
// public static void agentmain(String args, Instrumentation instrumentation) throws
// ClassNotFoundException, UnmodifiableClassException {
// instrumentation.addTransformer(new EnhanceFileTransformer(),true);
// instrumentation.retransformClasses(Class.forName("com.ggggght.learningjava8.controller.TestController"));
// }
// }
