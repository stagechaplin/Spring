package com.example.test01.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FirstController {
    @GetMapping("/test") // localhost:8080/ 뒤에 들어갈 URL 호출 값
        public String NiceToMeetYou(Model model){ // Model 선언, Model을 이용하여 이름 대신 변수명을 사용하여 불러옴.
        model.addAttribute("name","지올팍"); // Model에 변수를 등록할 때 사용하는 메서드
        return "test01"; // GetMapping을 통해서 반환할 머스타치 또는 타임리프문
        }
    @GetMapping("/bye")
     public String  seeYouNext(Model model) {
        model.addAttribute("name","독수리");
        return "goodbye";
    }
}
