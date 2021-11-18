// package com.ggggght.learningjava8.agent;
//
// import jdk.internal.org.objectweb.asm.ClassVisitor;
// import jdk.internal.org.objectweb.asm.ClassWriter;
// import jdk.internal.org.objectweb.asm.MethodVisitor;
// import jdk.internal.org.objectweb.asm.Opcodes;
//
// public class TraceVisitor extends ClassVisitor {
// private String className;
// private String methodName;
// public TraceVisitor(String className, String targetmethodname, ClassWriter writer) {
// super(Opcodes.ASM5, writer);
// this.className = className;
// this.methodName = targetmethodname;
// }
//
// @Override
// public MethodVisitor visitMethod(int i, String s, String s1, String s2, String[]
// strings) {
// MethodVisitor methodVisitor = cv.visitMethod(i, s, s1, s2, strings);
//
// if (methodName.equals(s)) {
// return new TraceAdviceAdapter(className, methodVisitor, i, s, s1);
// }
//
// return methodVisitor;
// }
// }
