package com.awsweb.springboot.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    // 반환되는 값 중 이메일을 통해 이미 생성된 사용자인지 확인
    Optional<User> findByEmail(String email);
}
