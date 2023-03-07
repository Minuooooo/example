package com.project.divide.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Member extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    @OneToMany(mappedBy = "member")
    private List<GroupMemberMap> groupMemberMaps = new ArrayList<>();

    private String email; //이메일
    private String password; //패스워드

    @Builder
    public Member(String email, String password) {
        this.email = email;
        this.password = password;
    }

    //연관관계 메서드
    public void addGroupMember(GroupMemberMap groupMemberMap) {
        groupMemberMaps.add(groupMemberMap);
        groupMemberMap.changeMember(this);
    }

    //비즈니스 로직


}
