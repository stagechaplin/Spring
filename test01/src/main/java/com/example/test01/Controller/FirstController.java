package com.example.test01.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FirstController {
    @GetMapping("/test")
        public String nicetomeetyou(Model model){
        model.addAttribute("name","지올팍");
        return "test01";
        }
}
