package com.project.divide.dto.sign;

import com.project.divide.domain.Member;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JoinRequestDto {

    @NotBlank(message = "이메일을 입력하세요")
    private String email;

    @NotBlank(message = "비밀번호를 입력하세요")
    private String password;

    @NotBlank(message = "비밀번호를 확인하세요")
    private String checkPassword;

    public Member toMember(JoinRequestDto joinRequestDto) {
        return Member.builder()
                .email(joinRequestDto.getEmail())
                .password(joinRequestDto.getPassword())
                .build();
    }
}
