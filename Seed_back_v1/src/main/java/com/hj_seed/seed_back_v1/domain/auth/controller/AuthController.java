package com.hj_seed.seed_back_v1.domain.auth.controller;

import com.hj_seed.seed_back_v1.domain.auth.data.dto.SignInResultDto;
import com.hj_seed.seed_back_v1.domain.auth.data.dto.SignUpResultDto;
import com.hj_seed.seed_back_v1.domain.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/sign-in")
    public SignInResultDto signIn(
            @RequestParam String username,
            @RequestParam String password) {

        log.info("[signIn] 로그인을 시도하고 있습니다. username : {}, pw : ****", username);
        SignInResultDto signInResultDto = authService.signIn(username, password);

        if (signInResultDto.getCode() == 0) {
            log.info("[signIn] 정상적으로 로그인되었습니다. username : {}, token : {}", username,
                    signInResultDto.getToken());
        }

        return signInResultDto;
    }

    @PostMapping("/sign-up")
    public SignUpResultDto signUp(
            @RequestParam String username,
            @RequestParam String password,
            @RequestParam String role) {
        log.info("[signUp] 회원가입을 수행합니다. username : {}, password : ****, role : {}",
                username, role);

        SignUpResultDto signUpResultDto = authService.signUp(username, password, role);

        log.info("[signUp] 회원가입을 완료했습니다. username : {}", username);
        return signUpResultDto;
    }

}
