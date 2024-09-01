package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Topcontroller {
	
    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("message", "トップ画面");
        return "top";
    }

}
