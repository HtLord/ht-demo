package ht.demo.error;

import lombok.Builder;
import lombok.Getter;
import org.apache.coyote.BadRequestException;

@Builder
@Getter
public class BadRequestWithErrorCodeException extends BadRequestException {

    private CodedError codedError;
}
