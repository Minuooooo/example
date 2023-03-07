package com.project.divide.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class GroupMemberRoleMap {

    @Id
    @GeneratedValue
    @Column(name = "group_member_role_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    private GroupMemberMap groupMemberMap;

    @ManyToOne(fetch = LAZY)
    private Role role;

    private String repetitionWeeks; //반복 주기
}
