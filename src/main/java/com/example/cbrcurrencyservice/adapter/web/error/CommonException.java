package com.example.cbrcurrencyservice.adapter.web.error;

import com.example.cbrcurrencyservice.adapter.web.error.common.ErrorCode;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CommonException extends RuntimeException{
    ErrorCode errorCode;
    HttpStatus httpStatus;
    String message;
}