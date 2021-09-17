package com.ggggght.learningjava8.compiler;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings("all")
public class UseJavac {
  private static final Logger LOGGER = LoggerFactory.getLogger(UseJavac.class);

    public static void main(String[] args) {
        final JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();

        if (compiler == null) {
            return;
        }

        String path = "F:\\H.java";
        final int result = compiler.run(null, null, null, new String[] {
            "-d", "F:", path
        });

        System.out.println(result);
    }
}
