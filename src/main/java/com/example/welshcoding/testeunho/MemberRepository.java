package com.example.welshcoding.testeunho;

import com.example.welshcoding.domain.Board;
import com.example.welshcoding.domain.Member;
import com.example.welshcoding.domain.Sns;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import javax.persistence.Query;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class MemberRepository{

    private final EntityManager em;

    public Member findById(Long memberId) {
        return em.find(Member.class, memberId);
    }

    @Transactional
    public void deleteById(Long memberId) {
        Member member = findById(memberId);
        em.remove(member);
    }

    public Member findOne(long id) {
    	return em.find(Member.class, id);
    }
    

//    public Sns findByIdSns(Long memberId) {
//
//        return em.find(Sns.class, memberId);
//    }


//    // 기존 데이터 조회
//    @Query("SELECT m FROM Member m")
//    Member getMemberData();
//
//    // 회원 데이터 업데이트
//    @Modifying
//    @Query("UPDATE Member m SET m.userName = :userName, m.userBio = :userBio, " +
//            "m.userFacebook = :userFacebook, m.userGithub = :userGithub, " +
//            "m.userTwitter = :userTwitter, m.userHomepage = :userHomepage WHERE m.memberId = :memberId")
//    void updateMemberData(@Param("memberId") Long memberId, @Param("userName") String userName,
//                          @Param("userBio") String userBio, @Param("userFacebook") String userFacebook,
//                          @Param("userGithub") String userGithub, @Param("userTwitter") String userTwitter,
//                          @Param("userHomepage") String userHomepage);
}