package com.hj_seed.seed_back_v1.domain.auth.data.dto;

public enum CommonResponseDto {
    SUCCESS(0, "Success"), FAIL(-1, "Fail");

    int code;

    String msg;

    CommonResponseDto(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
