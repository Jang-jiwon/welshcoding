package com.example.welshcoding.controller;

import com.example.welshcoding.service.SignupService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.welshcoding.domain.Member;
import com.example.welshcoding.domain.Sns;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/signup")
public class SignupController {

    private final SignupService signupService;

    @PostMapping("goSignup")
    public String signup(@RequestParam("userEmail") String userEmail,
                         @RequestParam("userPw") String userPw,
                         @RequestParam("userName") String userName
    ) {


        Member member = new Member();
        member.setUserEmail(userEmail);
        member.setUserPwd(userPw);
        member.setUserName(userName);
        Sns sns = new Sns();


//        sns.setUserFacebook(" ");
//        sns.setUserGithub(" ");
//        sns.setUserHomepage(" ");
//        sns.setUserTwitter(" ");
//        member.setSns(sns);

        member.setVelogPageName(userName);
//        member.setUserBio(" ");
//        member.setProfileImg(" ");
//        member.setUserBirth(" ");
//        member.setUserGender(" ");
//        member.setUserPhone(" ");
//        member.setTagsString(" ");

        
        log.info("userEmail : " + member.getUserEmail());
        log.info("userPw : " + member.getUserPwd());
        log.info("userName : " + member.getUserName());
        
        signupService.join(member);

        return "redirect:/login_out/gologin";
    }


    @PostMapping("dubcheck")
    @ResponseBody
    public String dubcheck(@RequestParam("email") String email) {
        return signupService.dubcheck(email);
    }


}
