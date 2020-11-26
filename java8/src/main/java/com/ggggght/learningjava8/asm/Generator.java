package com.ggggght.learningjava8.asm;

import com.sun.org.apache.bcel.internal.generic.NEW;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Generator {
    public static void main(String[] args) throws IOException {
        ClassReader classReader = new ClassReader("com.ggggght.learningjava8.asm.World");
        ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_MAXS);
        // 处理
        ClassVisitor classVisitor = new MyClassVisitor(classWriter);
        classReader.accept(classVisitor, ClassReader.SKIP_DEBUG);
        byte[] data = classWriter.toByteArray();
        // 输出到桌面上
        File f = new File(System.getProperty("user.home") + "/Desktop/World.class");
        FileOutputStream fout = new FileOutputStream(f);
        fout.write(data);
        fout.close();
        System.out.println("now generator cc success!!!!!");
    }
}
