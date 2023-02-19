package com.project.divide.dto.member;

import com.project.divide.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberSimplePasswordResponseDto {

    private String password;

    public static MemberSimplePasswordResponseDto toDto(Member member) {
        return new MemberSimplePasswordResponseDto(member.getPassword());
    }
}
