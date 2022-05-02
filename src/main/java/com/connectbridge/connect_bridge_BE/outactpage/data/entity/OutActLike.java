package com.connectbridge.connect_bridge_BE.outactpage.data.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
@Table(name = "outactlike")
public class OutActLike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="user_id")
    private Long userID;

    @Column(name = "outact_id")
    private Long outActID;

    @Builder
    public OutActLike(Long userID,Long outActID){
     this.userID = userID;
     this.outActID = outActID;
    }

    public OutActLike createActLike(Long userID, Long outActID){
        return OutActLike.builder()
                .userID(userID)
                .outActID(outActID)
                .build();
    }
}
