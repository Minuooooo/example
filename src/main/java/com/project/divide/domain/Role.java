package com.project.divide.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.BatchSize;

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

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "group_member_id")
    private GroupMember groupMember;

    @ManyToOne(fetch =  LAZY)
    @JoinColumn(name = "tag_id")
    private Tag tag;
    @OneToMany(mappedBy = "role")
    private List<CompleteDate> completeDates = new ArrayList<>();

    private String roleName; //역할명
    private LocalDate startDate; //시작일
    private LocalDate endDate; //종료일
    private String repetitionWeeks; //반복 주기

    //연관관계
    public void changeGroupMember(GroupMember groupMember) {
        this.groupMember = groupMember;
        groupMember.getRoles().add(this);
    }

    public void changeTag(Tag tag) {
        this.tag = tag;
        tag.getRoles().add(this);
    }

    public void addCompleteDate(CompleteDate completeDate) {
        completeDates.add(completeDate);
        completeDate.changeRole(this);
    }
}
