package com.shop.inditex.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class InditexBusinessException extends RuntimeException {

    private HttpStatus status;

    public InditexBusinessException(String message, Throwable cause) {
        super(message, cause);
    }

    public InditexBusinessException(String message) {
        super(message);
    }

    public InditexBusinessException(String message, HttpStatus httpStatus) {
        super(message);
        this.status = httpStatus;
    }
}
