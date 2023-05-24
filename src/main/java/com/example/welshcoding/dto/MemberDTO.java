package com.example.welshcoding.dto;

import com.example.welshcoding.domain.Sns;
import lombok.Data;

import java.util.Optional;

@Data
public class MemberDTO {
    private Long memberId;
    private String userEmail;
    private String userPwd;
    private String velogPageName;
    private String userName;
    private String userBio;
    private String profileImg;
    private String userBirth;
    private String userGender;
    private String userPhone;

    private Sns sns;
    private String userGithub;
    private String userTwitter;
    private String userFacebook;
    private String userHomepage;

    // null 일때 "" 를 리턴, 아닐때 userEmail 을 리턴하는 Getter를 GPT 에게 최적화 맡김ㅎㅎ.
    public Long getMemberId() {
        return Optional.ofNullable(memberId).orElse(null);
    }

    public String getUserEmail() {
        return Optional.ofNullable(userEmail).orElse("");
    }

    public String getUserPwd() {
        return Optional.ofNullable(userPwd).orElse("");
    }

    public String getVelogPageName() {
        return Optional.ofNullable(velogPageName).orElse("");
    }

    public String getUserName() {
        return Optional.ofNullable(userName).orElse("");
    }

    public String getUserBio() {
        return Optional.ofNullable(userBio).orElse("");
    }

    public String getProfileImg() {
        return Optional.ofNullable(profileImg).orElse("");
    }

    public String getUserBirth() {
        return Optional.ofNullable(userBirth).orElse("");
    }

    public String getUserGender() {
        return Optional.ofNullable(userGender).orElse("");
    }

    public String getUserPhone() {
        return Optional.ofNullable(userPhone).orElse("");
    }

    public Sns getSns() {
        return sns;
    }

    public String getUserGithub() {
        return Optional.ofNullable(userGithub).orElse("");
    }

    public String getUserTwitter() {
        return Optional.ofNullable(userTwitter).orElse("");
    }

    public String getUserFacebook() {
        return Optional.ofNullable(userFacebook).orElse("");
    }

    public String getUserHomepage() {
        return Optional.ofNullable(userHomepage).orElse("");
    }
}


//    public MemberDTO() {
//        this.memberId = memberId;
//        this.userEmail = userEmail;
//        this.userPwd = userPwd;
//        this.velogPageName = velogPageName;
//        this.userName = userName;
//        this.userBio = userBio;
//        this.profileImg = profileImg;
//        this.userBirth = userBirth;
//        this.userGender = userGender;
//        this.userPhone = userPhone;
//        this.userGithub = userGithub;
//        this.userTwitter = userTwitter;
//        this.userFacebook = userFacebook;
//        this.userHomepage = userHomepage;
//    }

//    public MemberDTO() {
//        this.memberId = memberId;
//        this.userEmail = userEmail;
//        this.userPwd = userPwd;
//        this.velogPageName = "벨로그 제목을 입력해주세요";
//        this.userName = userName;
//        this.userBio = "한 줄 소개를 입력해주세요 ^~^";
//        this.profileImg = "";
//        this.userBirth = "19970710";
//        this.userGender = "CORGI";
//        this.userPhone = "Phone";
//        this.userGithub = "Github 링크를 입력해주세요";
//        this.userTwitter = "Twitter 링크를 입력해주세요";
//        this.userFacebook = "Facebook 링크를 입력해주세요";
//        this.userHomepage = "개인 Homepage 링크를 입력해주세요";
//    }

