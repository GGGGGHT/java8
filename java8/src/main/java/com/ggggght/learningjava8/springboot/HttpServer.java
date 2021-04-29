package com.ggggght.learningjava8.springboot;

import org.springframework.stereotype.Component;

/**
 * @desc: com.ggggght.learningjava8.springboot
 * @date: 2021/4/23 21:40
 * @author: ggggght
 */
@Component
public class HttpServer implements Server {
	@Override
	public void start() {
		System.out.println("HTTP 服务器启动中。。。");
	}

	@Override
	public void stop() {
		System.out.println("HTTP 服务器关闭中");
	}
}
