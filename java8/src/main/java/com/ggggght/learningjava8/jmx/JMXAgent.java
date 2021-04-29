// package com.ggggght.learningjava8.jmx;
//
// //import com.sun.tools.attach.VirtualMachine;
// //import com.sun.tools.attach.VirtualMachineDescriptor;
// import com.sun.tools.attach.VirtualMachine;
// import com.sun.tools.attach.VirtualMachineDescriptor;
// import org.apache.ibatis.javassist.ClassPool;
// import org.apache.ibatis.javassist.CtClass;
// import org.apache.ibatis.javassist.CtMethod;
//
// import javax.management.MBeanServer;
// import javax.management.ObjectName;
// import java.lang.instrument.ClassFileTransformer;
// import java.lang.instrument.IllegalClassFormatException;
// import java.lang.instrument.Instrumentation;
// import java.lang.management.ManagementFactory;
// import java.lang.management.MemoryUsage;
// import java.security.ProtectionDomain;
// import java.util.List;
//
// /**
//  * @author ght
//  */
// public class JMXAgent implements ClassFileTransformer {
//     public static void main(String[] args) throws Exception {
//         List<VirtualMachineDescriptor> list = VirtualMachine.list();
//         // list.forEach(System.out::println);
//         VirtualMachineDescriptor virtualMachineDescriptor = list.get(3);
//         System.out.println("virtualMachineDescriptor.displayName() = " + virtualMachineDescriptor.displayName());
//         System.out.println("virtualMachineDescriptor.id = " + virtualMachineDescriptor.id());
//         System.out.println("virtualMachineDescriptor.provider() = " + virtualMachineDescriptor.provider());
//         System.out.println("virtualMachineDescriptor.toString() = " + virtualMachineDescriptor.toString());
//         VirtualMachine attach = VirtualMachine.attach(virtualMachineDescriptor.id());
// //        MemoryUsage memoryUsage =
//         MemoryUsage memoryUsage = new MemoryUsage(-1, 10240, 10240, -1);
//         System.out.println(memoryUsage);
//         MBeanServer ms = ManagementFactory.getPlatformMBeanServer();
//         ms.registerMBean(new Object(), new ObjectName("nananana"));
//
//     }
//
//     @Override
//     public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
//         try {
//             ClassPool cp = ClassPool.getDefault();
//             CtClass cc = cp.get("meituan.bytecode.jvmti.Base");
//             CtMethod m = cc.getDeclaredMethod("process");
//             m.insertBefore("{ System.out.println(\"start\"); }");
//             m.insertAfter("{ System.out.println(\"end\"); }");
//             return cc.toBytecode();
//         } catch (Exception e) {
//             e.printStackTrace();
//         }
//         return null;
//     }
//
//     public static void agentmain(String args, Instrumentation instrumentation) {
//         instrumentation.addTransformer(new JMXAgent(),true);
//         System.out.println("Agent Load done.");
//     }
// }
