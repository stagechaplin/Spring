package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FirstController {
    @GetMapping("/hi")
    public String niceToMeetYou(Model model){
        model.addAttribute("username","독수리 왕자");
    return "greetings";
    }
    @GetMapping("/bye")
    public String SeeYouNext(Model model){
        model.addAttribute("nickname","독수리 공주");
    return "goodbye";
    }
}
