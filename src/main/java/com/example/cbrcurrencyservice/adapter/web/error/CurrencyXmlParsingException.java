package com.example.cbrcurrencyservice.adapter.web.error;

import com.example.cbrcurrencyservice.adapter.web.error.common.ErrorCode;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CurrencyXmlParsingException extends CommonException {
    public CurrencyXmlParsingException(String message) {
        super(ErrorCode.PARSING_XML_EXCEPTION, HttpStatus.BAD_GATEWAY, message);
    }
}
