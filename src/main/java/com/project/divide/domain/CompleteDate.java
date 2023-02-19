package com.project.divide.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class CompleteDate extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "complete_date_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "role_id")
    private Role role;

    private LocalDate completedDate; //수행일

    public void changeRole(Role role) {
        this.role = role;
    }
}
