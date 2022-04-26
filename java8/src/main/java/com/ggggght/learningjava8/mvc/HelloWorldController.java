package com.ggggght.learningjava8.mvc;

import com.ggggght.learningjava8.jfr.HttpRequestEvent;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloWorldController {

    @ResponseBody
    @GetMapping("/sayHello")
    public String helloWorld() {
        HttpRequestEvent event = HttpRequestEvent.EVENT.get();
        System.out.println("event.isEnabled() = " + event.isEnabled());
        if (!event.isEnabled()) {
            return "Hello World!";
        }

        event.begin();
        event.method = "get";
        event.path = "/sayHello";
        event.commit();
        event.reset();

        return "Hello,World!";
    }
}
