// package com.ggggght.learningjava8.jvm;
//
// import java.util.ArrayList;
// import java.util.Objects;
// import sun.jvm.hotspot.memory.SystemDictionary;
// import sun.jvm.hotspot.oops.Klass;
// import sun.jvm.hotspot.oops.Oop;
// import sun.jvm.hotspot.oops.InstanceKlass;
// import sun.jvm.hotspot.runtime.VM;
// import sun.jvm.hotspot.tools.Tool;
//
// public class ClassStatistics extends Tool {
//
//   @Override
//   public void run() {
//
//     final SystemDictionary systemDictionary = VM.getVM().getSystemDictionary();
//     final ArrayList<InstanceKlass> instanceKlasses = new ArrayList<>();
//     final ArrayList<Oop> oops = new ArrayList<>();
//     systemDictionary.classesDo((klass, oop) -> {
//       if (klass instanceof InstanceKlass) {
//         instanceKlasses.add((InstanceKlass) klass);
//       }
//       oops.add(oop);
//     });
//
//     System.out.println("classes...");
//     instanceKlasses.stream().filter(Objects::nonNull).forEach(System.out::println);
//     System.out.println("-----------");
//     oops.stream().filter(Objects::nonNull).forEach(System.out::println);
//   }
//
//   public static void main(String[] args) {
//     ClassStatistics cs = new ClassStatistics();
//     cs.execute(args);
//   }
// }