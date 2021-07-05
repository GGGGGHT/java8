package com.ggggght.learningjava8.io;

import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;

/**
 * @author: 75685
 * @date: 2020/7/13
 */
public class FileIO {
	@Test
	public void fileTest() {
		ByteBuffer buffer = MappedByteBuffer.allocateDirect(10240);
		buffer.get();
		
		try {
			final FileInputStream fileInputStream = new FileInputStream(new File(Paths.get("").toUri()));
			final FileChannel channel = fileInputStream.getChannel();
			final MappedByteBuffer map = channel.map(FileChannel.MapMode.READ_WRITE, 0, 4096);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
		
		}
	}
	
	@Test
	public void _test() {
		int i = 10;
		i = i++ *2;
		System.out.println("i = " + i);
	}
}
