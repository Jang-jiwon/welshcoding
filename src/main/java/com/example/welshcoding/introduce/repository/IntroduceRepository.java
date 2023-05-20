package com.example.welshcoding.introduce.repository;

import com.example.welshcoding.domain.Board;
import com.example.welshcoding.domain.Introduce;
import com.example.welshcoding.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@Transactional
public class IntroduceRepository {

    private final EntityManager em;

    public IntroduceRepository(EntityManager em) {
        this.em = em;
    }

    @Transactional
    public void saveIntroduce(Introduce introduce) {
        if (introduce.getContent() == null) {
            em.persist(introduce);
        } else {
            deleteAllIntroduce();
            em.persist(introduce);
        }
    }

    public void deleteAllIntroduce() {
        Query query = em.createQuery("DELETE FROM Introduce");
        query.executeUpdate();
    }

    public Introduce findById(Long memberId) {
        try {
            return em.createQuery("select m from Introduce m where m.member.memberId = :memberId", Introduce.class)
                    .setParameter("memberId", memberId)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public Member findMemberById(Long memberId) {
        return em.find(Member.class, memberId);
    }



}
