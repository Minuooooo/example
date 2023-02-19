package com.project.divide.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.FetchType.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Member extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    @OneToMany(mappedBy = "member", fetch = LAZY)
    private List<GroupMember> groupMembers = new ArrayList<>();

    private String email; //이메일
    private String password; //패스워드
    private UploadFile memberImage; //회원 이미지

    @Builder
    public Member(String email, String password) {
        this.email = email;
        this.password = password;
    }

    //연관관계 메서드
    public void addGroupMember(GroupMember groupMember) {
        groupMembers.add(groupMember);
        groupMember.changeMember(this);
    }

    //비즈니스 로직


}
