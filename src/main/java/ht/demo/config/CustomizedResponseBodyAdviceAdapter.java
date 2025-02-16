package ht.demo.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

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
        var traceId =
            UUID.randomUUID();
        var inbound =
            LogInfo.builder()
                .type(LogInfoType.Inbound)
                .httpMethod(request.getMethod())
                .traceId(traceId)
                .uri(request.getURI())
                .build();
        var outbound =
            LogInfo.builder()
                .type(LogInfoType.Outbound)
                .httpMethod(request.getMethod())
                .traceId(traceId)
                .uri(request.getURI())
                .build();
        try {
            var inboundBody =
                IOUtils.toString(
                    request.getBody(),
                    StandardCharsets.UTF_8
                );
            inbound.setBody(
                inboundBody
            );
            var outboundBody =
                objectMapper.writeValueAsString(body);
            outbound.setBody(outboundBody);
            log.info("{}", inbound);
            log.info("{}", outbound);
        } catch (IOException e) {
            log.info(
                "[Failure-Log-Handling]{} : {}",
                inbound,
                e.getMessage()
            );
        }
        return body;
    }
}
