package com.ll.basic1.boundedContext.member.repository;

import com.ll.basic1.boundedContext.member.entity.Members;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Members, Long> {
    Optional<Members> findByUsername(String username);
}
