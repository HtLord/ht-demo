package ht.demo.error;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ErrorInfo {

    private String code;
    private String url;
    private String reason;
}
