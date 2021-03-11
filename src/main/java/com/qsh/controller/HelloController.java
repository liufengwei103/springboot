package com.qsh.controller;

import com.qsh.domain.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 2.
 *
 * @RestController =@Controller +@ResponseBody
 * @Controller： 标记这是一个controller
 * @ResponseBody: 将返回数据直接写给浏览器，而不是直接跳转到页面
 */
@RestController
@RequestMapping("/hello")
public class HelloController {

    /**
     * @Autowired spring的自动注入，将容器中的组件拿出来
     */
    @Autowired
    Car car;

    @RequestMapping("/car")
    public Car car() {
        return car;
    }

    @RequestMapping("/home")
    public String home() {
        return "hello,spring boot!";
    }
}
