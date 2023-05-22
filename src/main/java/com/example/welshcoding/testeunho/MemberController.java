package com.example.welshcoding.testeunho;

import com.example.welshcoding.domain.Member;
import com.example.welshcoding.domain.Sns;
import lombok.RequiredArgsConstructor;

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

    @GetMapping("/setPage")
    public String setPage(Model model) {
        Member member = (Member) session.getAttribute("member");
        long memberId = member.getMemberId();
        member = memberService.getMemberById(memberId);
        Sns sns = memberService.getMemberById(memberId).getSns();
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
    @Transactional
    public String deleteUser() {
        Member member = (Member) session.getAttribute("member");
        Long memberId = member.getMemberId();
        if (memberId == null) {
            // memberId 값이 세션에 없는 경우, 적절한 처리를 해주세요 (예: 로그인 페이지로 리다이렉트)
            return "redirect:/login";
        }

        memberService.deleteById(memberId);
        session.invalidate();

        return "redirect:/";
    }



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