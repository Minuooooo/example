package com.project.divide.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Repeat extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "repeat_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "role_id")
    private Role role;

    private Day day; //반복 요일

    public void changeRole(Role role) {
        this.role = role;
    }
}
