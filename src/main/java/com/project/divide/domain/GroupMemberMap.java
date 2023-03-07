package com.project.divide.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class GroupMemberMap extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "group_member_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "group_id")
    private Group group;

    @OneToMany(mappedBy = "groupMember")
    private List<GroupMemberRoleMap> groupMemberRoleMaps = new ArrayList<>();

    private String nickname; //그룹 내 별명
    private UploadFile groupMemberImage; //그룹 내 프로필 이미지

    @Enumerated(EnumType.STRING)
    private Permission permission; //권한

    //연관관계 메서드
    public void changeMember(Member member) {
        this.member = member;
    }


}
