package com.project.divide.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.FetchType.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class User extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "user_id")
    private Long id;

    @OneToOne(mappedBy = "user", fetch = LAZY)
    private Member member;

    private String email; //이메일
    private String password; //패스워드
    private String userImage; //유저 이미지 경로

    public void changeMember(Member member) {
        this.member = member;
    }
}
