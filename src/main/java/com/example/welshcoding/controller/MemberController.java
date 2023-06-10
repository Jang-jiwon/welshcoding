package com.example.welshcoding.controller;

import com.example.welshcoding.domain.Member;
import com.example.welshcoding.domain.Sns;
import com.example.welshcoding.dto.MemberDTO;
import com.example.welshcoding.service.MemberService;
import com.example.welshcoding.service.TestMemberService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpSession;

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
    private final TestMemberService testMemberService;
    @Autowired
    private HttpSession session;

//    @GetMapping("/setPage")
//    public String setPage(Model model,HttpSession session) {
//        // memberId를 사용하여 회원 정보를 조회하고 모델에 추가하는 로직을 작성하세요.
//    	Member member = (Member)session.getAttribute("member");
//    	long memberId = member.getMemberId();
//        member = memberService.getMemberById(memberId);
//        Sns sns = memberService.getMemberById(memberId).getSns();
//        model.addAttribute("member", member);
//        model.addAttribute("sns", sns);
//
//        return "setPage/setPage";
//    }
    
  

    
    
    @PostMapping("/profile")
    @ResponseBody
    public String imgsrc(@RequestParam("imgsrc") String imgsrc, HttpSession session) {
        String re = "";
        Member member = (Member) session.getAttribute("member");
        Member recentMember = memberService.findOne(member.getMemberId());
        try {
        	memberService.updateSrc(imgsrc, recentMember.getMemberId());
        	re="ok";
		} catch (Exception e) {
			e.printStackTrace();
		}
        return re;
    }

    @GetMapping("/setPage")
    public String setPage(Model model) {
        Member member = (Member) session.getAttribute("member");
        long memberId = member.getMemberId();
        member = memberService.getMemberById(memberId);
        Sns sns = memberService.getMemberById(memberId).getSns();
        model.addAttribute("recentMember", member);
        model.addAttribute("member", member);
        model.addAttribute("sns", sns);

        return "setPage/setPage";
    }

    @PostMapping("/setPage/edit")
    @Transactional
    public String editPage(@ModelAttribute MemberDTO form, Model model) {
        Member member = (Member) session.getAttribute("member");
        Long memberId = member.getMemberId();
        if (memberId == null) {
            // memberId 값이 세션에 없는 경우, 적절한 처리를 해주세요 (예: 로그인 페이지로 리다이렉트)
            return "redirect:/login";
        }

        memberService.editMemberById(memberId, form);

        return "redirect:/members/setPage";
    }

    @PostMapping("/setPage/edit2")
    @Transactional
    public String editPage2(@RequestParam(name = "velogPageName") String velogPageName, Model model) {
        Member member = (Member) session.getAttribute("member");
        Long memberId = member.getMemberId();
        if (memberId == null) {
            // memberId 값이 세션에 없는 경우, 적절한 처리를 해주세요 (예: 로그인 페이지로 리다이렉트)
            return "redirect:/login";
        }

        memberService.edit2MemberById(memberId, velogPageName);

        return "redirect:/members/setPage";
    }

    @PostMapping("/setPage/edit3")
    @Transactional
    public String editPage3(@ModelAttribute MemberDTO editSnsDTO) {
        Member member = (Member) session.getAttribute("member");
        Long memberId = member.getMemberId();
        if (memberId == null) {
            // memberId 값이 세션에 없는 경우, 적절한 처리를 해주세요 (예: 로그인 페이지로 리다이렉트)
            return "redirect:/login";
        }

        memberService.edit3MemberById(memberId, editSnsDTO);

        return "redirect:/members/setPage";
    }

    @GetMapping("/delete")
    public String deleteUser() {
        Member member = (Member) session.getAttribute("member");
        if (member == null) {
            // 만약 세션에 "member"가 없다면, 적절한 조치를 취하세요 (예: 로그인 페이지로 리다이렉트)
            return "redirect:/login";
        }
        Long memberId = member.getMemberId();
        memberService.deleteById(memberId);
        session.invalidate();

        System.out.println(" =============   Delete User Confirm. ============= ");

        return "redirect:/gologin";
    }

//    @GetMapping("/delete")
//    @Transactional
//    public String deleteUser() {
//        Member member = (Member) session.getAttribute("member");
//        Long memberId = member.getMemberId();
//        if (memberId == null) {
//            // memberId 값이 세션에 없는 경우, 적절한 처리를 해주세요 (예: 로그인 페이지로 리다이렉트)
//            return "redirect:/login";
//        }
//
//        memberService.deleteById(memberId);
//        session.invalidate();
//
//        return "redirect:/";
//    }



//    @PostMapping("/setPage/edit")
//    @Transactional
//    public String editPage(@ModelAttribute MemberDTO form, Model model) {
//        Long memberId = (Long) session.getAttribute("memberId");
//        if (memberId == null) {
//            // memberId 값이 세션에 없는 경우, 적절한 처리를 해주세요 (예: 로그인 페이지로 리다이렉트)
//            return "redirect:/login";
//        }
//
//        memberService.editMemberById(memberId, form);
//
//        return "redirect:/members/setPage/" + memberId;
//    }
//
//    @PostMapping("/setPage/edit2")
//    @Transactional
//    public String editPage2(@RequestParam(name = "velogPageName") String velogPageName, Model model) {
//        Long memberId = (Long) session.getAttribute("memberId");
//        if (memberId == null) {
//            // memberId 값이 세션에 없는 경우, 적절한 처리를 해주세요 (예: 로그인 페이지로 리다이렉트)
//            return "redirect:/login";
//        }
//
//        memberService.edit2MemberById(memberId, velogPageName);
//
//        return "redirect:/members/setPage/" + memberId;
//    }
//
//    @PostMapping("/setPage/edit3")
//    @Transactional
//    public String editPage3(@ModelAttribute MemberDTO editSnsDTO) {
//        Long memberId = (Long) session.getAttribute("memberId");
//        if (memberId == null) {
//            // memberId 값이 세션에 없는 경우, 적절한 처리를 해주세요 (예: 로그인 페이지로 리다이렉트)
//            return "redirect:/login";
//        }
//
//        memberService.edit3MemberById(memberId, editSnsDTO);
//
//        return "redirect:/members/setPage/" + memberId;
//    }
//
//    @GetMapping("/delete")
//    @Transactional
//    public String deleteUser() {
//        Long memberId = (Long) session.getAttribute("memberId");
//        if (memberId == null) {
//            // memberId 값이 세션에 없는 경우, 적절한 처리를 해주세요 (예: 로그인 페이지로 리다이렉트)
//            return "redirect:/login";
//        }
//
//        memberService.deleteById(memberId);
//
//        return "redirect:/";
//    }
}


//    @PostMapping("/setPage/{memberId}/edit")
//    @Transactional
//    public String editPage(@PathVariable("memberId") Long memberId
//            , @ModelAttribute MemberDTO form
//            , Model model) {
//
//        memberService.editMemberById(memberId, form);
//
//        return "redirect:/members/setPage/" + memberId;
//    }
//
//    @PostMapping("/setPage/{memberId}/edit2")
//    @Transactional
//    public String editPage2(@PathVariable("memberId") Long memberId
//            , @RequestParam(name = "velogPageName") String velogPageName
//            , Model model) {
//
//        memberService.edit2MemberById(memberId, velogPageName);
//
//        return "redirect:/members/setPage/" + memberId;
//    }
//
//
//    @PostMapping("/setPage/{memberId}/edit3")
//    @Transactional
//    public String editPage3(@PathVariable("memberId") Long memberId
//            , @ModelAttribute MemberDTO editSnsDTO) {
//
//        memberService.edit3MemberById(memberId, editSnsDTO);
//
//        return "redirect:/members/setPage/" + memberId;
//
//        // 처음 시도했던 방법 (1차시도)
////    @PostMapping("/setPage/{memberId}/edit3")
////    @Transactional
////    public String editPage3(@PathVariable("memberId") Long memberId
////            , @ModelAttribute String email, String Github, String Twitter,
////                            String Facebook, String UserHomepage
////            , Model model) {
////
////        memberService.edit3MemberById(memberId, email, Github, Twitter, Facebook, UserHomepage);
////
////        return "redirect:/members/setPage/" + memberId;
//
//    }
//    @GetMapping("/delete/{memberId}")
//    @Transactional
//    public String deleteUser(@PathVariable("memberId") Long memberId) {
//
//        memberService.deleteById(memberId);
//
//        return "redirect:/";
//    }
//}