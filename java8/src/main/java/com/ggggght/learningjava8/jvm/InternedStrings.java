// package com.ggggght.learningjava8.jvm;
//
// import sun.jvm.hotspot.memory.SystemDictionary;
// import sun.jvm.hotspot.oops.InstanceKlass;
// import sun.jvm.hotspot.oops.OopField;
// import sun.jvm.hotspot.runtime.VM;
// import sun.jvm.hotspot.tools.Tool;
//
// public class InternedStrings extends Tool {
//
// @Override
// public void run() {
// // Use Reflection-like API to reference String class and String.value field
// SystemDictionary dict = VM.getVM().getSystemDictionary();
// InstanceKlass stringKlass = (InstanceKlass) dict.find("java/lang/String", null, null);
// OopField valueField = (OopField) stringKlass.findField("value", "[C");
//
// // Counters
// long[] stats = new long[2];
//
// // Iterate through the String Pool printing out each String object
// VM.getVM().getStringTable().stringsDo(s -> {
// s.printValueOn(System.out);
// System.out.println();
// stats[0]++;
// stats[1] += s.getObjectSize() + valueField.getValue(s).getObjectSize();
// });
//
// System.out.printf("%d strings with total size %d\n", stats[0], stats[1]);
// }
//
// public static void main(String[] args) {
// // Use default SA tool launcher
// new InternedStrings().execute(args);
// }
// }