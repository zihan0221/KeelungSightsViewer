package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

    @GetMapping("/main")
    public String getMain() {
        return "main"; // 返回的字符串對應於你的 HTML 文件名稱，不包含擴展名
    }
}
