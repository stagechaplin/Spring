package com.example.demo.controller;

import com.example.demo.dto.MemberForm;
import com.example.demo.entity.Member;
import com.example.demo.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
@Slf4j
@Controller
public class MemberController {

    @Autowired
    MemberRepository memeberRepository;
    @GetMapping("/signup")
    public String signUpPage(){
        return "Members/new";
    }
    @PostMapping("/join")
    public String join(MemberForm memberForm) {
        log.info(memberForm.toString());
        //1. DTO -> entity
        Member member = memberForm.toEntity();
        log.info(member.toString());

        //2. repository로 entity를 DB에 저장
        Member saved = memeberRepository.save(member);
        log.info(saved.toString());
        return "";
    }

@GetMapping("/members/{id}")
public String show(@PathVariable Long id, Model model){
    Member member = memeberRepository.findById(id).orElse(null);
    model.addAttribute("member",member);
    return "members/show";
}

@GetMapping("/members")
public String index(Model model){
        Iterable<Member>members= memeberRepository.findAll();
        model.addAttribute("members",members);
    return "members/index";
}
}
