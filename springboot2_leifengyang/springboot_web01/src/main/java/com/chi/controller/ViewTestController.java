package com.chi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author chi
 * @Description: TODO
 * @date 2021/11/3 09:03
 * @Version 1.0
 */
@Controller
public class ViewTestController {

    @GetMapping("/viewHellow")
    public String viewHello(Model model){

        model.addAttribute("msg", "Hello, I'm from ViewTestController.");
        model.addAttribute("link","http://www.baidu.com");
        return "success";
    }
}
