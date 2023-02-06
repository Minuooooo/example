package com.project.divide.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Member extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "group_id")
    private Group group;

    @OneToMany(mappedBy = "member")
    private List<Role> roles;

    private String nickname; //그룹 내 별명

    @Enumerated(EnumType.STRING)
    private Permission permission; //권한

    //연관관계
    public void changeUser(User user) {
        this.user = user;
        user.changeMember(this);
    }

    public void changeGroup(Group group) {
        this.group = group;
        group.getMembers().add(this);
    }


}
