package com.hj_seed.seed_back_v1.domain.auth.service.impl;

import com.hj_seed.seed_back_v1.config.security.JwtTokenProvider;
import com.hj_seed.seed_back_v1.domain.auth.data.dto.CommonResponseDto;
import com.hj_seed.seed_back_v1.domain.auth.data.dto.SignInResultDto;
import com.hj_seed.seed_back_v1.domain.auth.data.dto.SignUpResultDto;
import com.hj_seed.seed_back_v1.domain.user.data.entity.User;
import com.hj_seed.seed_back_v1.domain.user.data.repository.UserRepository;
import com.hj_seed.seed_back_v1.domain.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;

    @Override
    public SignUpResultDto signUp(String username, String password, String role) {
        log.info("[getSignUpResult] 회원 가입 정보 전달");
        User user;
        if (role.equalsIgnoreCase("admin")) {
            user = User.builder()
                    .username(username)
                    .password(password)
                    .roles(Collections.singletonList("ROLE_ADMIN"))
                    .build();
        } else {
            user = User.builder()
                    .username(username)
                    .password(password)
                    .roles(Collections.singletonList("ROLE_USER"))
                    .build();
        }

        User savedUser = userRepository.save(user);
        SignUpResultDto signUpResultDto = new SignUpResultDto();

        log.info("[getSignUpResult] userEntity 값이 들어왔는지 확인 후 결과값 주입");
        if (!savedUser.getUsername().isEmpty()) {
            log.info("[getSignUpResult] 정상 처리 완료");
            setSuccessResult(signUpResultDto);
        } else {
            log.info("[getSignUpResult] 실패 처리 완료");
            setFailResult(signUpResultDto);
        }

        return signUpResultDto;
    }

    @Override
    public SignInResultDto signIn(String username, String password) {
        log.info("[getSignInResult] signDataHandler 로 회원 정보 요청");

        User user = userRepository.findByUsername(username);
        log.info("[getSignInResult] Username : {}", username);

        log.info("[getSignInResult] 패스워드 비교 수행");
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException();
        }
        log.info("[getSignInResult] 패스워드 일치");

        log.info("[getSignInResult] SignInResultDto 객체 생성");
        SignInResultDto signInResultDto = SignInResultDto.builder()
                .token(jwtTokenProvider.createToken(String.valueOf(user.getUsername()), user.getRoles()))
                .build();

        log.info("[getSignInResult] SignInResultDto 객체에 값 주입");
        setSuccessResult(signInResultDto);

        return signInResultDto;
    }

    private void setSuccessResult(SignUpResultDto result) {
        result.setSuccess(true);
        result.setCode(CommonResponseDto.SUCCESS.getCode());
        result.setMsg(CommonResponseDto.SUCCESS.getMsg());
    }

    private void setFailResult(SignUpResultDto result) {
        result.setSuccess(false);
        result.setCode(CommonResponseDto.FAIL.getCode());
        result.setMsg(CommonResponseDto.FAIL.getMsg());
    }
}
