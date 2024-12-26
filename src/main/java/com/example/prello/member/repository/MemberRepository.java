package com.example.prello.member.repository;

import com.example.prello.member.auth.MemberAuth;
import com.example.prello.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Member findByIdAndAuth(Long id, MemberAuth auth);
}
