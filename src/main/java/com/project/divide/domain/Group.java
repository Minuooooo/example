package com.project.divide.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Group extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "group_id")
    private Long id;

    @OneToMany(mappedBy = "group")
    private List<Member> members;

    private String groupName; //그룹명
    private String link; //링크
    private String groupImage; //그룹 이미지 경로
}
