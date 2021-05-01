package com.ggggght.learningjava8.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @desc: com.ggggght.learningjava8.controller
 * @date: 2021/3/27 14:09
 * @author: ggggght
 */
@RestController
public class TestController {

	@GetMapping("/hello")
	public String sayHello() {
		return "hello world!";
	}
}
