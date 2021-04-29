package com.ggggght.learningjava8.agent;

import com.sun.tools.attach.AgentInitializationException;
import com.sun.tools.attach.AgentLoadException;
import com.sun.tools.attach.AttachNotSupportedException;
import com.sun.tools.attach.VirtualMachine;

import java.io.IOException;

public class Start {
	public static void main(String[] args) throws IOException, AttachNotSupportedException, AgentLoadException, AgentInitializationException {
		VirtualMachine vm = VirtualMachine.attach("67182");
		vm.loadAgent("/Users/admin/IdeaProjects/java8/java8/target/classes/trace.jar", "com.ggggght.learningjava8.controller.TestController.sayHello");
		vm.detach();
	}
}
