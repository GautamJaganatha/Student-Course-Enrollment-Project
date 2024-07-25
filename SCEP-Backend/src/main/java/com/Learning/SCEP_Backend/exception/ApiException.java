package com.Learning.SCEP_Backend.exception;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class ApiException {
    private final String message;
    private final HttpStatus httpStatus;
    private LocalDateTime time;

    public ApiException(String message,  HttpStatus httpStatus, LocalDateTime time) {
        this.message = message;
        this.httpStatus = httpStatus;
        this.time = time;
    }

    public String getMessage() {
        return message;
    }



    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public LocalDateTime getTime() {
        return time;
    }

}
