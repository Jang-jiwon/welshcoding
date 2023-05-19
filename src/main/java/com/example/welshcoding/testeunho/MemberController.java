package com.example.welshcoding.testeunho;

import com.example.welshcoding.domain.Member;
import com.example.welshcoding.domain.Sns;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/setPage/{memberId}")
    public String setPage(@PathVariable("memberId") Long memberId, Model model) {
        // memberId를 사용하여 회원 정보를 조회하고 모델에 추가하는 로직을 작성하세요.
        Member member = memberService.getMemberById(memberId);
        Sns sns = memberService.getMemberById(memberId).getSns();
        model.addAttribute("member", member);
        model.addAttribute("sns", sns);

        return "setPage/setPage";
    }

    @PostMapping("/setPage/{memberId}/edit")
    @Transactional
    public String editPage(@PathVariable("memberId") Long memberId
            , @ModelAttribute EditFormDTO form
            , Model model) {

        memberService.editMemberById(memberId, form);

        return "redirect:/members/setPage/" + memberId;
    }

    @PostMapping("/setPage/{memberId}/edit2")
    @Transactional
    public String editPage2(@PathVariable("memberId") Long memberId
            , @RequestParam(name = "velogPageName") String velogPageName
            , Model model) {

        memberService.edit2MemberById(memberId, velogPageName);

        return "redirect:/members/setPage/" + memberId;
    }

    @PostMapping("/setPage/{memberId}/edit3")
    @Transactional
    public String editPage3(@PathVariable("memberId") Long memberId
            , @ModelAttribute EditSnsDTO editSnsDTO) {

        memberService.edit3MemberById(memberId, editSnsDTO);

        return "redirect:/members/setPage/" + memberId;

//    @PostMapping("/setPage/{memberId}/edit3")
//    @Transactional
//    public String editPage3(@PathVariable("memberId") Long memberId
//            , @ModelAttribute String email, String Github, String Twitter,
//                            String Facebook, String UserHomepage
//            , Model model) {
//
//        memberService.edit3MemberById(memberId, email, Github, Twitter, Facebook, UserHomepage);
//
//        return "redirect:/members/setPage/" + memberId;

    }
    @GetMapping("/delete/{memberId}")
    @Transactional
    public String deleteUser(@PathVariable("memberId") Long memberId) {

        memberService.deleteById(memberId);

        return "redirect:/";
    }
}