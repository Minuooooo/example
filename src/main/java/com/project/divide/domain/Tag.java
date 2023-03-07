package com.project.divide.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.util.Lazy;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Tag extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "tag_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    private Tag tag;

    @OneToMany(mappedBy = "tag")
    private List<Role> roles = new ArrayList<>();

    private String tagName; //태그명
}
