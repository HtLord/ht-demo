package ht.demo.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdviceAdapter;

import java.lang.reflect.Type;

@ControllerAdvice
@Slf4j
@RequiredArgsConstructor
public class CustomizedRequestBodyAdviceAdapter extends RequestBodyAdviceAdapter {

    private final ObjectMapper objectMapper;

    @Override
    public boolean supports(
        MethodParameter methodParameter,
        Type targetType,
        Class<? extends HttpMessageConverter<?>> converterType
    ) {
        return true;
    }

    @Override
    public Object afterBodyRead(
        Object body,
        HttpInputMessage inputMessage,
        MethodParameter parameter,
        Type targetType,
        Class<? extends HttpMessageConverter<?>> converterType
    ) {
        try {
            log.info(
                "[Inbound][Body] {}",
                objectMapper.writeValueAsString(body)
            );
        } catch (JsonProcessingException e) {
            log.error("[Inbound][Body] Failure to unwrap body, traceId: {}", "fake-trace-id");
        }
        return body;
    }
}
