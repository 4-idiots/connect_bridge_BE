package com.connectbridge.connect_bridge_BE.domain.repository;

import com.connectbridge.connect_bridge_BE.domain.entity.MemberEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

@Repository
@RequiredArgsConstructor
public class MemberRepository {
    private final EntityManager em;

    public void saveMember(MemberEntity memberEntity){
        em.persist(memberEntity);
    }

    public MemberEntity findUserByUserID(String userID){
        TypedQuery<MemberEntity> query = em.createQuery("select m from MemberEntity as m where m.userID = ?1", MemberEntity.class)
                .setParameter(1, userID);
        return query.getSingleResult();
    }


}