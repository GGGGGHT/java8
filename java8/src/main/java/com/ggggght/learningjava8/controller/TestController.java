package com.ggggght.learningjava8.controller;

import com.ggggght.learningjava8.agent.TraceAdviceAdapter;
import com.ggggght.learningjava8.excel.LearningPOI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
