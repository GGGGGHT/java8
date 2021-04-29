package com.ggggght.learningjava8.springboot;

/**
 * @desc: com.ggggght.learningjava8.springboot
 * @date: 2021/4/23 21:39
 * @author: ggggght
 */
public interface Server {
	/**
	 * 启动服务器
	 */
	void start();

	/**
	 * 关闭服务器
	 */
	void stop();

	enum Type {
		HTTP,
		FTP
	}
}
