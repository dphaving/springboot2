package org.tad.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.tad.bean.ResPerson;

import java.util.Date;

/**
 * @ClassName ResponseController
 * @Description 内容协商测试
 * @Author Tad
 * @Date 2024/5/26 16:40
 * @Version 1.0
 */
@RestController
public class ResponseController {

    @ResponseBody
    @GetMapping("/test/person")
    public ResPerson getPerson() {
        ResPerson resPerson = new ResPerson();
        resPerson.setName("Jack");
        resPerson.setAge(18);
        resPerson.setBirthday(new Date());
        return resPerson;
    }

}
