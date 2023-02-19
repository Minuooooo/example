package com.project.divide.service.member;

import com.project.divide.domain.Member;
import com.project.divide.dto.member.MemberSimpleEmailResponseDto;
import com.project.divide.dto.member.MemberSimplePasswordResponseDto;
import com.project.divide.dto.sign.JoinRequestDto;
import com.project.divide.repository.member.MemberRepository;
import com.project.divide.service.member.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
class MemberServiceTest {

    @InjectMocks
    private MemberService memberService;

    @Mock
    private MemberRepository memberRepository;

    @Test
    @DisplayName("join 서비스 테스트")
    public void joinTest() {
        //given
        JoinRequestDto joinRequestDto = new JoinRequestDto("mmw7741@gmail.com", "1", "1");

        //when
        Member joinMember = memberService.join(joinRequestDto);

        //then
        then(memberRepository).should(times(1)).save(joinMember);
        assertThat(joinRequestDto.getEmail()).isEqualTo(joinMember.getEmail());
    }

    @Test
    @DisplayName("회원가입 실패 테스트")
    public void joinExceptionTest() {
        //given
        JoinRequestDto joinRequestMember = new JoinRequestDto("mmw7741@gmail.com", "1", "1");
        JoinRequestDto incorrectPasswordRequestMember = new JoinRequestDto("mmw7741@gmail.com", "1", "12");
        JoinRequestDto duplicateRequestMember = new JoinRequestDto("mmw7741@gmail.com", "12", "12");

        given(memberRepository.findByEmail("mmw7741@gmail.com")).willThrow(new IllegalStateException());

        //when, then
        assertThatThrownBy(() -> memberService.join(incorrectPasswordRequestMember))
                .isInstanceOf(IllegalStateException.class);
        assertThatThrownBy(() -> memberService.join(duplicateRequestMember))
                .isInstanceOf(IllegalStateException.class);
    }

    @Test
    @DisplayName("findEmail 서비스 테스트")
    public void findEmail() {
        //given
        Member member = new Member("mmw7741@gmail.com", "1");
        given(memberRepository.findById(anyLong())).willReturn(Optional.of(member));

        //when
        MemberSimpleEmailResponseDto result = memberService.findEmail(1L);

        //then
        assertThat(result.getEmail()).isEqualTo(member.getEmail());
    }

    @Test
    @DisplayName("이메일 찾기 실패 테스트")
    public void findEmailExceptionTest() {
        //given
        Member member = new Member("mmw7741@gmail.com", "1");
        given(memberRepository.findById(anyLong())).willReturn(Optional.empty());

        //when, then
        assertThatThrownBy(() -> memberService.findEmail(1L))
                .isInstanceOf(IllegalStateException.class);
    }

    @Test
    @DisplayName("findPassword 서비스 테스트")
    public void findPassword() {
        //given
        Member member = new Member("mmw7741@gmail.com", "1");
        given(memberRepository.findById(anyLong())).willReturn(Optional.of(member));

        //when
        MemberSimplePasswordResponseDto result = memberService.findPassword(1L);

        //then
        assertThat(result.getPassword()).isEqualTo(member.getPassword());
    }

    @Test
    @DisplayName("비밀번호 찾기 실패 테스트")
    public void findPasswordExceptionTest() {
        //given
        Member member = new Member("mmw7741@gmail.com", "1");
        given(memberRepository.findById(anyLong())).willReturn(Optional.empty());

        //when, then
        assertThatThrownBy(() -> memberService.findPassword(1L))
                .isInstanceOf(IllegalStateException.class);
    }


}
