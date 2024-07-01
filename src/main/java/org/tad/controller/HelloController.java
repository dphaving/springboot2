package org.tad.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tad.bean.Person;
import org.tad.bean.User;

/**
 * @ClassName HelloController
 * @Description 控制器
 * @Author Tad
 * @Date 2024/4/21 16:22
 * @Version 1.0
 */
@RestController
public class HelloController {

    @RequestMapping("/hello")
    public String hello() {
        return "hello world";
    }

    @RequestMapping("/user")
    public User person(User user) {
        System.out.println(user);
        return user;
    }
}
