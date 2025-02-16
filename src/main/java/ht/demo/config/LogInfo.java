package ht.demo.config;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpMethod;

import java.net.URI;

@Getter
@Builder
public class LogInfo {

    private LogInfoType type;
    private String traceId;
    private HttpMethod httpMethod;
    private URI uri;
    @Setter
    private String body;

    public String toString() {
        return "[%s][%s][%s][%s][Body]: %s"
            .formatted(
                type,
                traceId,
                httpMethod,
                uri,
                body
            );
    }
}
