// package com.ggggght.learningjava8.jmx;
//
// //import com.sun.tools.attach.VirtualMachine;
// //import com.sun.tools.attach.VirtualMachineDescriptor;
//
// // import com.sun.tools.attach.VirtualMachine;
// // import com.sun.tools.attach.VirtualMachineDescriptor;
// import org.apache.ibatis.javassist.ClassPool;
// import org.apache.ibatis.javassist.CtClass;
// import org.apache.ibatis.javassist.CtMethod;
// import org.openjdk.jol.vm.VirtualMachine;
//
// import javax.management.MBeanServer;
// import javax.management.ObjectInstance;
// import javax.management.ObjectName;
// import java.lang.instrument.ClassFileTransformer;
// import java.lang.instrument.IllegalClassFormatException;
// import java.lang.instrument.Instrumentation;
// import java.lang.management.*;
// import java.security.ProtectionDomain;
// import java.util.ArrayDeque;
// import java.util.Arrays;
// import java.util.List;
// import java.util.Set;
// import java.util.stream.Collectors;
//
// /**
// * @author ght
// */
// public class JMXAgent implements ClassFileTransformer {
// public static void main(String[] args) throws Exception {
// // RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();
// // System.out.println("ManagementFactory.getRuntimeMXBean().getVmName() = " +
// runtimeMXBean.getVmName());
// // System.out.println("ManagementFactory.getRuntimeMXBean().getName() = " +
// runtimeMXBean.getName());
// //
// // System.out.println("runtimeMXBean.getSpecName() = " + runtimeMXBean.getSpecName());
// List<VirtualMachineDescriptor> list = VirtualMachine.list();
// // list.forEach(System.out::println);
//
// VirtualMachineDescriptor virtualMachineDescriptor = list.stream().filter(t ->
// t.displayName().contains("JMXAgent")).collect(Collectors.toList()).get(0);
// System.out.println("virtualMachineDescriptor.displayName() = " +
// virtualMachineDescriptor.displayName());
// System.out.println("virtualMachineDescriptor.id = " + virtualMachineDescriptor.id());
// System.out.println("virtualMachineDescriptor.provider() = " +
// virtualMachineDescriptor.provider());
// System.out.println("virtualMachineDescriptor.toString() = " +
// virtualMachineDescriptor.toString());
// VirtualMachine attach = VirtualMachine.attach(virtualMachineDescriptor.id());
// MemoryUsage memoryUsage = new MemoryUsage(-1, 10240, 10240, -1);
// System.out.println(memoryUsage);
// MBeanServer ms = ManagementFactory.getPlatformMBeanServer();
// ms.registerMBean(new QueueSampler(new ArrayDeque<>()), new
// ObjectName("com.ggggght:type=nananana"));
//
// System.out.println("ms.getMBeanCount() = " + ms.getMBeanCount());
// Set<ObjectInstance> mbeans = ms.queryMBeans(null, null);
// for (ObjectInstance mbean : mbeans) {
// System.out.println("beanname: " + mbean.getClassName() + " ,className: " +
// mbean.getClassName());
// }
//
// List<GarbageCollectorMXBean> garbageCollector =
// ManagementFactory.getGarbageCollectorMXBeans();
// garbageCollector.stream().forEach(g -> {
// // System.out.println("g.getCollectionCount() = " + g.getCollectionCount());
// // System.out.println("g.getCollectionTime() = " + g.getCollectionTime());
// System.out.println("g.getName() = " + g.getName());
// System.out.println(Arrays.toString(g.getMemoryPoolNames()));
// });
//
// OperatingSystemMXBean systemMXBean = ManagementFactory.getOperatingSystemMXBean();
// System.out.println("systemMXBean.getName() = " + systemMXBean.getName());
// System.out.println("systemMXBean.getArch() = " + systemMXBean.getArch());
// System.out.println("systemMXBean.getObjectName() = " + systemMXBean.getObjectName());
// System.out.println("systemMXBean.getSystemLoadAverage() = " +
// systemMXBean.getSystemLoadAverage());
// System.out.println("systemMXBean.getVersion() = " + systemMXBean.getVersion());
// System.out.println("systemMXBean.getAvailableProcessors() = " +
// systemMXBean.getAvailableProcessors());
//
// CompilationMXBean compilationMXBean = ManagementFactory.getCompilationMXBean();
// System.out.println("compilationMXBean.getName() = " + compilationMXBean.getName());
// System.out.println("compilationMXBean.getTotalCompilationTime() = " +
// compilationMXBean.getTotalCompilationTime());
// System.out.println("compilationMXBean.getClass() = " + compilationMXBean.getClass());
// System.out.println("compilationMXBean.getObjectName() = " +
// compilationMXBean.getObjectName());
//
// ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
// System.out.println("threadMXBean.getThreadCount() = " + threadMXBean.getThreadCount());
// System.out.println("Arrays.toString(threadMXBean.getAllThreadIds()) = " +
// Arrays.toString(threadMXBean.getAllThreadIds()));
// System.out.println("threadMXBean.getTotalStartedThreadCount() = " +
// threadMXBean.getTotalStartedThreadCount());
// System.out.println("threadMXBean.getDaemonThreadCount() = " +
// threadMXBean.getDaemonThreadCount());
// System.out.println("Arrays.toString(threadMXBean.getThreadInfo(threadMXBean.getAllThreadIds()))
// = " + Arrays.toString(threadMXBean.getThreadInfo(threadMXBean.getAllThreadIds())));
// System.out.println("threadMXBean.getPeakThreadCount() = " +
// threadMXBean.getPeakThreadCount());
//
// MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();
// System.out.println("memoryMXBean.getHeapMemoryUsage().getInit() = " +
// memoryMXBean.getHeapMemoryUsage().getInit());
// System.out.println("memoryMXBean.getHeapMemoryUsage().getMax() = " +
// memoryMXBean.getHeapMemoryUsage().getMax());
// System.out.println("memoryMXBean.getHeapMemoryUsage().getCommitted() = " +
// memoryMXBean.getHeapMemoryUsage().getCommitted());
// System.out.println("memoryMXBean.getHeapMemoryUsage().getUsed() = " +
// memoryMXBean.getHeapMemoryUsage().getUsed());
//
// System.out.println();
//
// MBeanServer platformMBeanServer = ManagementFactory.getPlatformMBeanServer();
// System.out.println(platformMBeanServer);
//
// }
//
// public static void agentmain(String args, Instrumentation instrumentation) {
// instrumentation.addTransformer(new JMXAgent(), true);
// System.out.println("Agent Load done.");
// }
//
// @Override
// public byte[] transform(ClassLoader loader, String className, Class<?>
// classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws
// IllegalClassFormatException {
// try {
// ClassPool cp = ClassPool.getDefault();
// CtClass cc = cp.get("meituan.bytecode.jvmti.Base");
// CtMethod m = cc.getDeclaredMethod("process");
// m.insertBefore("{ System.out.println(\"start\"); }");
// m.insertAfter("{ System.out.println(\"end\"); }");
// return cc.toBytecode();
// } catch (Exception e) {
// e.printStackTrace();
// }
// return null;
// }
// }
