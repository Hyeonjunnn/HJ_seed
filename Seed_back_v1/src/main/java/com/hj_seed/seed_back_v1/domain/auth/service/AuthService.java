package com.hj_seed.seed_back_v1.domain.auth.service;

import com.hj_seed.seed_back_v1.domain.auth.data.dto.SignInResultDto;
import com.hj_seed.seed_back_v1.domain.auth.data.dto.SignUpResultDto;

public interface AuthService {
    SignUpResultDto signUp(String username, String password, String role);

    SignInResultDto signIn(String username, String password);
}
