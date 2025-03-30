package com.hj_seed.seed_back_v1.common.exception;

import com.hj_seed.seed_back_v1.common.exception.message.ExceptionMessage;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.io.Serial;

@Getter
public class PostException extends RuntimeException {

    private final String type;

    private final HttpStatus status;

    public PostException(ExceptionMessage message) {
        super(message.getMessage());

        this.type = message.name();
        this.status = message.getStatus();
    }
}
