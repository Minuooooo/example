package com.project.divide.service.member;

import com.project.divide.domain.Member;
import com.project.divide.domain.StoreFile;
import com.project.divide.domain.UploadFile;
import com.project.divide.dto.member.MemberSimpleEmailResponseDto;
import com.project.divide.dto.member.MemberSimplePasswordResponseDto;
import com.project.divide.dto.sign.JoinRequestDto;
import com.project.divide.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;
    private final StoreFile storeFile; //이렇게 의존을 해야되는 게 맞나?

    //회원가입 서비스 반환형을 Member 해도 되나?
    @Transactional
    public Member join(JoinRequestDto joinRequestDto) {

        //비밀번호 확인 로직
        if (joinRequestDto.getPassword() != joinRequestDto.getPassword()) {
            throw new IllegalStateException("비밀번호를 다시 확인하세요");
        }

        Member joinMember = joinRequestDto.toMember(joinRequestDto);

        emailValidateDuplicate(joinMember); //중복 이메일 검증

        memberRepository.save(joinMember); //회원 저장

        return joinMember;
    }

    //이메일 찾기 서비스
    public MemberSimpleEmailResponseDto findEmail(Long id) {

        Member findMember = memberRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("이메일을 찾을 수 없습니다."));

        return MemberSimpleEmailResponseDto.toDto(findMember);
    }

    //비밀번호 찾기 서비스
    public MemberSimplePasswordResponseDto findPassword(Long id) {

        Member findMember = memberRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("비밀번호를 찾을 수 없습니다."));

        return MemberSimplePasswordResponseDto.toDto(findMember);
    }

    //로그인 서비스


    //나의 이메일 조회 서비스
    public MemberSimpleEmailResponseDto getMyEmail(Long id) {

        //회원이 무조건 존재(로그인 상태) -> get()
        Member findMember = memberRepository.findById(id).get();
        return MemberSimpleEmailResponseDto.toDto(findMember);
    }

    //프로필 변경 서비스
    //검토 필요
    @Transactional
    public void updateMyProfile(Long id, MultipartFile multipartFile) throws IOException {

        UploadFile uploadFile = storeFile.storeFile(multipartFile);
        memberRepository.updateProfile(id, uploadFile.getStoreFile());
    }

    //로그아웃 서비스

    //탈퇴 서비스
    //로그아웃 로직 추가 필요
    @Transactional
    public void deleteMember(Member member) {

        memberRepository.delete(member); //회원 삭제
    }

    //중복 이메일 검증 로직
    public void emailValidateDuplicate(Member member) {
        memberRepository.findByEmail(member.getEmail())
                .ifPresent(m -> {
                    throw new IllegalStateException("중복된 이메일입니다.");
                });
    }


}
