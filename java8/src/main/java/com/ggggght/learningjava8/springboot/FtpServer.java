package com.ggggght.learningjava8.springboot;

import org.springframework.stereotype.Component;

/**
 * @desc: com.ggggght.learningjava8.springboot
 * @date: 2021/4/23 21:41
 * @author: ggggght
 */
@Component
public class FtpServer implements Server {

	@Override
	public void start() {
		System.out.println("FTP start...");
	}

	@Override
	public void stop() {
		System.out.println("FTP close...");
	}

}
