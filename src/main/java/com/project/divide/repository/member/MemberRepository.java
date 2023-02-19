package com.project.divide.repository.member;

import com.project.divide.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    public Optional<Member> findByEmail(String email);

    @Query("update Member m set m.memberImage =:memberImage where m.memberId =:memberId")
    public void updateProfile(@Param("memberId") Long memberId, @Param("memberImage") String memberImage);


}
