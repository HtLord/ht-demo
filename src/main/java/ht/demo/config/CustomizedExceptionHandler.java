package ht.demo.config;

import ht.demo.error.BadRequestWithErrorCodeException;
import ht.demo.error.ErrorInfo;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

public class CustomizedExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BadRequestWithErrorCodeException.class)
    @ResponseBody ErrorInfo handleBadRequest(
        HttpServletRequest req,
        BadRequestWithErrorCodeException ex
    ) {
        return ErrorInfo.builder()
            .url(req.getRequestURL().toString())
            .reason(ex.getMessage())
            .code(ex.getCodedError().getCode())
            .build();
    }
}
