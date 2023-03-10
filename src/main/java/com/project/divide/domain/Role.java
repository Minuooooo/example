package com.project.divide.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Role extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "role_id")
    private Long id;

    @OneToMany(mappedBy = "role")
    private List<GroupMemberRoleMap> groupMemberRoleMaps = new ArrayList<>();

    @ManyToOne(fetch =  LAZY)
    @JoinColumn(name = "tag_id")
    private Tag tag;
    @OneToMany(mappedBy = "role")
    private List<CompleteDate> completeDates = new ArrayList<>();

    private String roleName; //역할명
    private LocalDate startDate; //시작일
    private LocalDate endDate; //종료일
    private boolean isAgreed; //동의 여부

    //연관관계


    public void changeTag(Tag tag) {
        this.tag = tag;
        tag.getRoles().add(this);
    }

    public void addCompleteDate(CompleteDate completeDate) {
        completeDates.add(completeDate);
        completeDate.changeRole(this);
    }
}
