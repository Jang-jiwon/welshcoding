package com.example.welshcoding.testeunho;

import com.example.welshcoding.domain.Sns;
import lombok.Data;

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

    public MemberDTO() {
        this.memberId = memberId;
        this.userEmail = userEmail;
        this.userPwd = userPwd;
        this.velogPageName = velogPageName;
        this.userName = userName;
        this.userBio = userBio;
        this.profileImg = profileImg;
        this.userBirth = userBirth;
        this.userGender = userGender;
        this.userPhone = userPhone;
        this.userGithub = userGithub;
        this.userTwitter = userTwitter;
        this.userFacebook = userFacebook;
        this.userHomepage = userHomepage;
    }
}
