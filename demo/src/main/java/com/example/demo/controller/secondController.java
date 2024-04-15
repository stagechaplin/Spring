package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class secondController {
    @GetMapping("/random-quote")
    public String randomQuote(Model model){
        String[] quotes = {
                "행복은 습관이다. 그것을 몸에 지니라" + "-허버드-",
                "저는 못하는게 아니라," + "안하는 거라구요... -독수리 명언 중-",
                "저도 한다면 잘 할수 있어요, 지금..."+ "이것 좀 보고있어요. -딴거하다 걸렸을 때-",
                "아.. 저 자료조사 하고 있었어요 -게임하다 걸렸을때 -"
        };
        int randInt = (int)(Math.random()*quotes.length);
        model.addAttribute("randomQuote",quotes[randInt]);
        return "quote";
    }
}
