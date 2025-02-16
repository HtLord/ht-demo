package ht.demo.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@ControllerAdvice
@Slf4j
@RequiredArgsConstructor
public class CustomizedResponseBodyAdviceAdapter implements ResponseBodyAdvice {

    private final ObjectMapper objectMapper;


    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        var msg =
            LogInfo.builder()
                .type(LogInfoType.Outbound)
                .traceId("fake-trace-id")
                .httpMethod(request.getMethod())
                .uri(request.getURI())
                .build();
        try {
            var bodyString =
                objectMapper.writeValueAsString(body);
            msg.setBody(bodyString);
            log.info("{}", msg);
        } catch (JsonProcessingException e) {
            msg.setBody(e.getMessage());
            log.info("{}", msg);
        }
        return body;
    }
}
