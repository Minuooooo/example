package com.project.divide.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.BatchSize;

import java.time.LocalDate;
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
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch =  LAZY)
    @JoinColumn(name = "tag_id")
    private Tag tag;

    @BatchSize(size = 100)
    @OneToMany(mappedBy = "role")
    private List<Repeat> repeats;

    @BatchSize(size = 100)
    @OneToMany(mappedBy = "role")
    private List<Schedule> schedules;

    private String roleName; //역할명
    private LocalDate startDate; //시작일
    private LocalDate endDate; //종료일

    //연관관계
    public void changeMember(Member member) {
        this.member = member;
        member.getRoles().add(this);
    }

    public void changeTag(Tag tag) {
        this.tag = tag;
        tag.getRoles().add(this);
    }

    public void addRepeat(Repeat repeat) {
        repeats.add(repeat);
        repeat.changeRole(this);
    }

    public void addSchedule(Schedule schedule) {
        schedules.add(schedule);
        schedule.changeRole(this);
    }
}
