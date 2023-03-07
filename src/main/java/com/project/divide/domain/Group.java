package com.project.divide.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "`group`")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Group extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "group_id")
    private Long id;

    @OneToMany(mappedBy = "group")
    private List<GroupMemberMap> groupMemberMaps = new ArrayList<>();

    @OneToMany(mappedBy = "group")
    private List<Tag> tags = new ArrayList<>();

    private String groupName; //그룹명
    private String link; //링크
    private UploadFile groupImage; //그룹 이미지
}
