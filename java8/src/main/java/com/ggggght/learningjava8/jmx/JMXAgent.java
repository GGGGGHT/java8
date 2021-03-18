package com.ggggght.learningjava8.jmx;

import com.sun.tools.attach.VirtualMachine;
import com.sun.tools.attach.VirtualMachineDescriptor;

import java.util.List;

/**
 * @author ght
 */
public class JMXAgent {
    public static void main(String[] args) throws Exception {
        List<VirtualMachineDescriptor> list = VirtualMachine.list();
        // list.forEach(System.out::println);
        VirtualMachineDescriptor virtualMachineDescriptor = list.get(3);
        System.out.println("virtualMachineDescriptor.displayName() = " + virtualMachineDescriptor.displayName());
        System.out.println("virtualMachineDescriptor.id = " + virtualMachineDescriptor.id());
        System.out.println("virtualMachineDescriptor.provider() = " + virtualMachineDescriptor.provider());
        System.out.println("virtualMachineDescriptor.toString() = " + virtualMachineDescriptor.toString());
        VirtualMachine attach = VirtualMachine.attach(virtualMachineDescriptor.id());
//        MemoryUsage memoryUsage =
    }
}
