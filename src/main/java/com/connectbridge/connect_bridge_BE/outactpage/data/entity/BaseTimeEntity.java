package com.connectbridge.connect_bridge_BE.outactpage.data.entity;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseTimeEntity {

    @CreatedDate
    private LocalDateTime createDate; // Entity 생성,저장 될 때 시간이 저장.

   // @LastModifiedDate
   // private LocalDateTime modifiedDate; // 조회한 Entity 값을 변경할 때 시간이 저장.
}
