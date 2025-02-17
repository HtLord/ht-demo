package ht.demo.config;

import ht.demo.error.CustomizedException;
import ht.demo.error.ErrorInfo;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionHandlerCollection {

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(CustomizedException.class)
    @ResponseBody ErrorInfo customizedExceptionHandlerAndResponse(
        HttpServletRequest req,
        CustomizedException ex
    ) {
        return ErrorInfo.builder()
            .code(ex.getCode())
            .url(req.getRequestURI())
            .reason(ex.getMessage())
            .build();
    }
}
