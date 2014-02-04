package com.macs.starter.service;

import com.macs.starter.model.Hello;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by Maksim_Alipov on 1/30/14.
 */
@Controller
@Component
@RequestMapping("/starter/v1/serv")
public class HelloService {

    private static final String template = "Hello, %s!";

    @RequestMapping("/hello")
    public @ResponseBody
    Hello hello(
            @RequestParam(value="name", required=false, defaultValue="World") String name) {
        return new Hello(String.format(template, name));
    }
}
