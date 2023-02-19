package com.project.divide.dto.member;

import com.project.divide.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberSimpleEmailResponseDto {

    private String email;

    public static MemberSimpleEmailResponseDto toDto(Member member) {
        return new MemberSimpleEmailResponseDto(member.getEmail());
    }
}
