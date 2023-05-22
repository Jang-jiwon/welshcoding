package com.example.welshcoding.testeunho;

import com.example.welshcoding.domain.Board;
import com.example.welshcoding.domain.Member;
import com.example.welshcoding.domain.Sns;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

//    private MemberDTO convertToDTO(Member member) {
//        MemberDTO memberDTO = new MemberDTO();
//        memberDTO.setUserName(member.getUserName());
//        memberDTO.setUserBio(member.getUserBio());
//        memberDTO.setSns(member.getSns()); // Set SNS data
//        memberDTO.setVelogPageName(member.getVelogPageName()); // Set Velog_page_name data
//        // Set other properties as needed
//        return memberDTO;
//    }

    public Member getMemberById(Long memberId) {
        Member member = memberRepository.findById(memberId);
        MemberDTO memberDTO = new MemberDTO();

        memberDTO.setMemberId(member.getMemberId());
        memberDTO.setUserEmail(member.getUserEmail());
        memberDTO.setUserPwd(member.getUserPwd());
        memberDTO.setUserName(member.getUserName());

        // NULL인 컬럼들에 대한 기본값 설정
        memberDTO.setUserBio(member.getUserBio() != null ? member.getUserBio() : "");
        memberDTO.setProfileImg(member.getProfileImg() != null ? member.getProfileImg() : "");
        memberDTO.setUserBirth(member.getUserBirth() != null ? member.getUserBirth() : "");
        memberDTO.setUserGender(member.getUserGender() != null ? member.getUserGender() : "");
        memberDTO.setUserPhone(member.getUserPhone() != null ? member.getUserPhone() : "");

        // Sns 정보가 null인 경우에 대한 예외 처리
        if (member.getSns() != null) {
            memberDTO.setUserGithub(member.getSns().getUserGithub() != null ? member.getSns().getUserGithub() : "");
            memberDTO.setUserTwitter(member.getSns().getUserTwitter() != null ? member.getSns().getUserTwitter() : "");
            memberDTO.setUserFacebook(member.getSns().getUserFacebook() != null ? member.getSns().getUserFacebook() : "");
            memberDTO.setUserHomepage(member.getSns().getUserHomepage() != null ? member.getSns().getUserHomepage() : "");
        } else {
            memberDTO.setUserGithub("");
            memberDTO.setUserTwitter("");
            memberDTO.setUserFacebook("");
            memberDTO.setUserHomepage("");
        }

        return member;
    }



//    public MemberDTO getMemberById(Long memberId) {
//        Member member = memberRepository.findById(memberId);
//        MemberDTO memberDTO = new MemberDTO();
//
//        memberDTO.setMemberId(member.getMemberId());
//        memberDTO.setUserEmail(member.getUserEmail());
//        memberDTO.setUserPwd(member.getUserPwd());
//        memberDTO.setUserName(member.getUserName());
//
//        // NULL인 컬럼들에 대한 기본값 설정
//        memberDTO.setUserBio(member.getUserBio() != null ? member.getUserBio() : "");
//        memberDTO.setProfileImg(member.getProfileImg() != null ? member.getProfileImg() : "");
//        memberDTO.setUserBirth(member.getUserBirth() != null ? member.getUserBirth() : "");
//        memberDTO.setUserGender(member.getUserGender() != null ? member.getUserGender() : "");
//        memberDTO.setUserPhone(member.getUserPhone() != null ? member.getUserPhone() : "");
//        memberDTO.setUserGithub(memberDTO.getUserGithub() != null ? memberDTO.getUserGithub() : "");
//        memberDTO.setUserTwitter(memberDTO.getUserTwitter() != null ? memberDTO.getUserTwitter() : "");
//        memberDTO.setUserGithub(memberDTO.getUserGithub() != null ? memberDTO.getUserGithub() : "");
//        memberDTO.setUserGithub(memberDTO.getUserGithub() != null ? memberDTO.getUserGithub() : "");
//
//        // 나머지 NULL인 컬럼들에 대한 처리
//
//        return memberDTO;
//    }

    // 예외처리 전 원본
//    public Member getMemberById(Long memberId) {
//       return memberRepository.findById(memberId);
//    }


    @Transactional
    public void editMemberById(Long memberId, MemberDTO form) {
        Member member = memberRepository.findById(memberId);
        member.setUserName(form.getUserName());
        member.setUserBio(form.getUserBio());
    }

    @Transactional
    public void edit2MemberById(Long memberId, String velogPageName) {
        Member member = memberRepository.findById(memberId);
        member.setVelogPageName(velogPageName);
    }

//    @Transactional
//    public void edit3MemberById(Long memberId, String email, String Github, String Twitter,
//                                String Facebook, String UserHomepage) {
//        Member member = memberRepository.findById(memberId);
//        Sns sns = memberRepository.findById(memberId).getSns();
//
//        member.setUserEmail(email);
//
//        sns.setUserGithub(Github);
//        sns.setUserTwitter(Twitter);
//        sns.setUserFacebook(Facebook);
//        sns.setUserHomepage(UserHomepage);
//    }


    // SNS 정보가 Null 일 경우 예외처리
    @Transactional
    public void edit3MemberById(Long memberId, MemberDTO editSnsDTO) {
        Member member = memberRepository.findById(memberId);
        Sns sns = member.getSns();

        if (sns == null) {
            // SNS 정보가 없는 경우, 새로 생성하여 할당합니다.
            sns = new Sns();
            member.setSns(sns);
        }

        // SNS 정보 업데이트
        member.setUserEmail(editSnsDTO.getUserEmail());
        sns.setUserGithub(editSnsDTO.getUserGithub());
        sns.setUserTwitter(editSnsDTO.getUserTwitter());
        sns.setUserFacebook(editSnsDTO.getUserFacebook());
        sns.setUserHomepage(editSnsDTO.getUserHomepage());
    }
    
    public Member findOne(long memberId) {
		return memberRepository.findOne(memberId);
	}

    // SNS 수정 기능 원본 (예외처리 X)
//    @Transactional
//    public void edit3MemberById(Long memberId, EditSnsDTO editSnsDTO) {
//
//        Member member = memberRepository.findById(memberId);
////        Sns sns = memberRepository.findById(memberId).getSns();
//
//        member.setUserEmail(editSnsDTO.getUserEmail());
//        member.getSns().setUserGithub(editSnsDTO.getUserGithub());
//        member.getSns().setUserHomepage(editSnsDTO.getUserHomepage());
//        member.getSns().setUserTwitter(editSnsDTO.getUserTwitter());
//        member.getSns().setUserFacebook(editSnsDTO.getUserFacebook());
//    }

    @Transactional
    public void deleteById(Long memberId) {
        memberRepository.deleteById(memberId);
    }
}
