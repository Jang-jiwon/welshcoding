package com.example.welshcoding.introduce.controller;

import com.example.welshcoding.domain.Introduce;
import com.example.welshcoding.domain.Member;
import com.example.welshcoding.introduce.repository.IntroduceRepository;
import com.example.welshcoding.introduce.service.IntroduceService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller

public class IntroduceController {
    private final IntroduceService introduceService;

    @Autowired
    public IntroduceController(IntroduceService introduceService) {
        this.introduceService = introduceService;
    }

    @GetMapping("/introduce")
    public String getIntroduce(Model model, HttpSession session) {
        // memberId를 얻어오는 코드를 추가

        Long memberId = (Long)session.getAttribute("memberId");
        System.out.println(memberId);

        Introduce introduce = introduceService.findById(memberId);

        model.addAttribute("introduce", introduce);

        return "introduce/body";
    }


    @PostMapping("/introduce/save")
    public String saveIntroduce(@ModelAttribute Introduce introduce) {

        introduceService.saveIntroduce(introduce.getContent(), introduce.getMember());
        System.out.println("saveIntroduce 메소드 호출");

        return "introduce/body";
    }

}
