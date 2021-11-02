package com.chi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author chi
 * @Description: TODO
 * @date 2021/11/2 08:01
 * @Version 1.0
 */
@Controller
public class RequestController {

    @GetMapping("/goto")
    public String goToPage(HttpServletRequest request) {

        request.setAttribute("msg", "success...");
        request.setAttribute("code", 200);

        return "forward:/success";
    }

    @ResponseBody
    @GetMapping("/success")
    public Map<String, Object> success(@RequestAttribute("msg") String msg,
                                       @RequestAttribute("code") Integer code,
                                       HttpServletRequest request) {

        Object msg1 = request.getAttribute("msg");

        Map<String, Object> map = new HashMap<>();

        map.put("RequestAttribute_msg", msg);
        map.put("HttpServletRequest_msg", msg1);

        return map;
    }
}
