// package com.ggggght.learningjava8.agent;
//
// // import jdk.internal.org.objectweb.asm.ClassReader;
// // import jdk.internal.org.objectweb.asm.ClassWriter;
//
// // import jdk.internal.org.objectweb.asm.ClassReader;
// // import jdk.internal.org.objectweb.asm.ClassWriter;
//
// import java.lang.instrument.ClassFileTransformer;
// import java.lang.instrument.IllegalClassFormatException;
// import java.security.ProtectionDomain;
//
// public class EnhanceFileTransformer implements ClassFileTransformer {
// private static final String TRAGETCLASSNAME =
// "com/ggggght/learningjava8/controller/TestController";
// private static final String TARGETMETHODNAME = "sayHello";
//
//
//
// @Override
// public byte[] transform(ClassLoader loader, String className, Class<?>
// classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws
// IllegalClassFormatException {
// if (className.startsWith("java") || className.startsWith("jdk") ||
// className.startsWith("javax") || className.startsWith("sun")
// || className.startsWith("com/sun") || className.startsWith("com/ggggght/agent") ||
// !className.equals(TRAGETCLASSNAME)) {
// //return null会执行原来的字节码
// return null;
// }
//
//
// ClassReader reader = new ClassReader(classfileBuffer);
// ClassWriter writer = new ClassWriter(reader, ClassWriter.COMPUTE_FRAMES |
// ClassWriter.COMPUTE_MAXS);
// reader.accept(new TraceVisitor(className, TARGETMETHODNAME, writer),
// ClassReader.EXPAND_FRAMES);
// return writer.toByteArray();
// }
// }
