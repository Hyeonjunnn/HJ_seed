package com.hj_seed.seed_back_v1.domain.user.data.repository;

import com.hj_seed.seed_back_v1.domain.user.data.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
