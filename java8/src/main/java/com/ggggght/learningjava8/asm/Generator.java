package com.ggggght.learningjava8.asm;

// import org.objectweb.asm.ClassReader;
// import org.objectweb.asm.ClassVisitor;
// import org.objectweb.asm.ClassWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import org.springframework.asm.ClassReader;
import org.springframework.asm.ClassVisitor;
import org.springframework.asm.ClassWriter;

public class Generator {

	public static void main(String[] args) throws IOException {
		ClassReader classReader = new ClassReader("com.ggggght.learningjava8.asm.World");
		ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_MAXS);
		// 处理
		// ClassVisitor classVisitor = new MyClassVisitor(classReader);
		// classReader.accept(classVisitor, ClassReader.SKIP_DEBUG);
		byte[] data = classWriter.toByteArray();
		// 输出到桌面上
		File f = new File(System.getProperty("user.home") + "/Desktop/World.class");
		FileOutputStream fout = new FileOutputStream(f);
		fout.write(data);
		fout.close();
		System.out.println("now generator cc success!!!!!");
	}

}
