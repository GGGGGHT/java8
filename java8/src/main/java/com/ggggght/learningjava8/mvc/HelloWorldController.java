package com.ggggght.learningjava8.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloWorldController {

	@ResponseBody
	@RequestMapping
	public String helloWorld() {
		return "Hello,World!";
	}

}
