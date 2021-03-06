package com.chi.controller;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author chi
 * @Description: TODO
 * @date 2021/11/1 15:27
 * @Version 1.0
 */
@RestController
public class ParameterTestController {

    /**
     * @Description /car/3/owner/lisi?age=18&inters=baskletball&inters=game
     */
    @GetMapping("/car/{id}/owner/{username}")
    public Map<String, Object> getCar(@PathVariable("id") Integer id,
                                      @PathVariable("username") String name,
                                      @PathVariable Map<String, String> pv,
                                      @RequestHeader("User-Agent") String userAgent,
                                      @RequestHeader Map<String, String> header,
                                      @RequestParam("age") Integer age,
                                      @RequestParam("inters") List<String> inters,
                                      @RequestParam Map<String, String> params,
                                      @CookieValue("_ga") String _ga,
                                      @CookieValue("_ga") Cookie cookie) {


        Map<String, Object> map = new HashMap<>();

//        map.put("id",id);
//        map.put("name",name);
//        map.put("pv",pv);
//        map.put("userAgent",userAgent);
//        map.put("headers",header);
        map.put("age", age);
        map.put("inters", inters);
        map.put("params", params);
        map.put("_ga", _ga);
        System.out.println(cookie.getName() + "===>" + cookie.getValue());
        return map;
    }

    @PostMapping("/save")
    public Map postMethod(@RequestBody String content) {
        Map<String, Object> map = new HashMap<>();
        map.put("content", content);

        return map;
    }

    /**
     *
     * @Description //矩阵变量
     * 1.请求url示例
     * /cars/sell;low=34;brand=byd,audi,yd
     *
     * 2.说明
     * 但是springboot默认是禁用了矩阵变量,需要手动开启
     * 原理: 对于路径的处理,UrlPathHelper会进行解析.WebMvcAutoConfiguration默认是true.
     */
    @GetMapping("/cars/{path}")
    public Map carSell(@MatrixVariable("low") Integer low,
                       @MatrixVariable("brand") List<String> brand,
                       @PathVariable("path") String path) {

        Map<String, Object> map = new HashMap<>();
        map.put("low", low);
        map.put("brand", brand);
        map.put("path", path);

        return map;
    }

}
