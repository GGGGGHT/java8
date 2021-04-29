package com.ggggght.learningjava8.agent;

import jdk.internal.org.objectweb.asm.Label;
import jdk.internal.org.objectweb.asm.MethodVisitor;
import jdk.internal.org.objectweb.asm.Opcodes;
import jdk.internal.org.objectweb.asm.Type;
import jdk.internal.org.objectweb.asm.commons.AdviceAdapter;

import java.time.LocalDateTime;


public class TraceAdviceAdapter extends AdviceAdapter {
	private final String className;
	private final String methodName;
	private final Type[] methodArgs;
	private final String[] parameterNames;
	private final int[] lvtSlotIndex;


	protected TraceAdviceAdapter(String className, MethodVisitor methodVisitor, int methodAccess, String methodName, String methodDesc) {
		super(Opcodes.ASM5, methodVisitor, methodAccess, methodName, methodDesc);
		this.className = className;
		this.methodName = methodName;
		this.methodArgs = Type.getArgumentTypes(methodDesc);
		this.parameterNames = new String[this.methodArgs.length];
		this.lvtSlotIndex = computeLvtSlotIndices(isStatic(methodAccess), this.methodArgs);
	}

	@Override
	public void visitLocalVariable(String name, String description, String signature, Label start, Label end, int index) {
		for (int i = 0; i < this.lvtSlotIndex.length; ++i) {
			if (this.lvtSlotIndex[i] == index) {
				this.parameterNames[i] = name;
			}
		}
	}


	@Override
	protected void onMethodEnter() {
		if ("<init>".equals(methodName) || "<clinit>".equals(methodName)) {
			return;
		}

		mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
		mv.visitTypeInsn(NEW, "java/lang/StringBuilder");
		mv.visitInsn(DUP);

		mv.visitMethodInsn(INVOKESPECIAL, "java/lang/StringBuilder", "<init>", "()V", false);
		mv.visitMethodInsn(INVOKESTATIC, "java/time/LocalDateTime", "now", "()Ljava/time/LocalDateTime;", false);
		mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(Ljava/lang/Object;)Ljava/lang/StringBuilder;", false);
		mv.visitLdcInsn("进来方法了");
		mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(Ljava/lang/Object;)Ljava/lang/StringBuilder;", false);
		mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "toString", "()Ljava/lang/String;", false);
		mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
		//
		// mv.visitInsn(RETURN);
		// mv.visitMethodInsn(INVOKESTATIC, "com/ggggght/learningjava8/agent/TraceAdviceAdapter", "sayHello", "()V", false);
	}

	@Override
	protected void onMethodExit(int i) {
		if ("<init>".equals(methodName) || "<clinit>".equals(methodName)) {
			return;
		}

		mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
		mv.visitLdcInsn("hello world!");
		mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
		// mv.visitMethodInsn(INVOKESTATIC, "com/ggggght/learningjava8/agent/TraceAdviceAdapter", "sayBye", "()V", false);
	}

	private static int[] computeLvtSlotIndices(boolean isStatic, Type[] paramTypes) {
		int[] lvtIndex = new int[paramTypes.length];
		int nextIndex = isStatic ? 0 : 1;
		for (int i = 0; i < paramTypes.length; ++i) {
			lvtIndex[i] = nextIndex;
			if (isWideType(paramTypes[i])) {
				nextIndex += 2;
			}
			else {
				++nextIndex;
			}
		}
		return lvtIndex;
	}

	private static boolean isWideType(Type aType) {
		return aType == Type.LONG_TYPE || aType == Type.DOUBLE_TYPE;
	}

	private static boolean isStatic(int access) {
		return (access & 8) > 0;
	}

	public static void sayHello() {
		System.out.println(LocalDateTime.now() + " enter method...");
	}

	public static void sayBye() {
		System.out.println(LocalDateTime.now() + " exit method...");
	}
}
