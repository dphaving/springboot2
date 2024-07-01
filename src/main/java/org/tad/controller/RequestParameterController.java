package org.tad.controller;

import com.alibaba.fastjson2.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.tad.bean.Car;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName RequestParameterController
 * @Description 请求参数控制器
 * @Author Tad
 * @Date 2024/5/6 22:57
 * @Version 1.0
 */
@Controller
@Slf4j
public class RequestParameterController {


//    、
//    、@MatrixVariable、

    /**
     * @PathVariable、@RequestHeader
     * @RequestParam、@CookieValue
     */
    @GetMapping("/car/{id}")
    public Map<String, Object> getCar(
            @PathVariable("id") Integer carId,
            @RequestHeader("brand") String carBrand,
            @RequestParam Map<String, String> carInfo,
            @CookieValue("session_id") String sessionId
    ) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("path-->carId", carId);
        map.put("header-->carBrand", carBrand);
        map.put("param-->carInfo", carInfo);
        map.put("cookie-->sessionId", sessionId);
        return map;
    }

    /**
     * @RequestBody
     */
    @PostMapping("/car")
    public Map<String, Object> addCar(@RequestBody Car car) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("carBrand", car.getBrand());
        map.put("carPrice", car.getPrice());
        log.info(JSONObject.toJSONString(map));
        return map;
    }

    /**
     * @ModelAttribute
     */

    /**
     * @MatrixVariable
     */


    @GetMapping("/params")
    public String testParam(Map<String, Object> map, Model model, HttpServletRequest request, HttpServletResponse response) {
        map.put("name", "jack");
        model.addAttribute("age", "18");
        request.setAttribute("gender", "male");
        Cookie cookie = new Cookie("lang", "zh_cn");
        cookie.setDomain("localhost");
        response.addCookie(cookie);
        return "forward:/success";
    }

    @ResponseBody
    @GetMapping("/success")
    public Map success(@RequestAttribute("name") String name, @RequestAttribute("age") String age, HttpServletRequest request) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("name", name);
        map.put("age", age);
        map.put("gender", request.getAttribute("gender"));

        return map;
    }

}
