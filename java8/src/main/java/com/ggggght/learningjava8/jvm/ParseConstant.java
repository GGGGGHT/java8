package com.ggggght.learningjava8.jvm;

import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

public class ParseConstant {
	static byte[] bytes;

	static {
		try {
			bytes = Files.readAllBytes(Paths.get("/Users/admin/Hello.class"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		magic();
	}


	public static void magic() {
		for (int i = 0; i < 4; i++) {
			System.out.println("bytes[i] = " + bytes[i]);
		}


	}

}
