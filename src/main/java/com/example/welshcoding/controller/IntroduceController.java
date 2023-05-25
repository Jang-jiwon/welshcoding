package com.example.welshcoding.controller;

import com.example.welshcoding.domain.Introduce;
import com.example.welshcoding.domain.Member;
import com.example.welshcoding.service.IntroduceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller

public class IntroduceController {
    private final IntroduceService introduceService;

    @Autowired
    public IntroduceController(IntroduceService introduceService) {
        this.introduceService = introduceService;
    }

    //    @GetMapping("/introduce/{memberId}")
    public String getIntroduce(@PathVariable Long memberId, Model model, HttpSession session) {
        // memberId를 얻어오는 코드를 추가
        Member member = (Member) session.getAttribute("member");
//        Long memberId = (Long)session.getAttribute("memberId");
        System.out.println(memberId);

        Introduce introduce = introduceService.findById(memberId);

        model.addAttribute("introduce", introduce);
        System.out.println("================");
        System.out.println(introduce.getContent());
        return "introduce/body";
    }


    @PostMapping("/introduce/{memberId}/save")
    public String saveIntroduce(@PathVariable(name = "memberId") Long memberId
            , @RequestParam(name = "content") String content) {

        introduceService.saveIntroduce(memberId, content);
        // System.out.println(memberId);
        // System.out.println("+++++++++++++++++++++++++");
        // System.out.println(content);

        return "redirect:/mainBoard";
    }

    @PostMapping("/introduce/{memberId}/editIntro")
    public String editIntroduce(@PathVariable(name = "memberId") Long memberId
            , @RequestParam(name = "content") String content) {

        introduceService.editIntro(memberId, content);

        return "redirect:/mainBoard";
    }

}
