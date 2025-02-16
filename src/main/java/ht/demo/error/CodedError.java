package ht.demo.error;

import org.apache.coyote.BadRequestException;

public interface CodedError {

    String getCode();
    String getMessage();
    String getMessageFmt();

    default BadRequestException generateBadRequestException() {
        return new BadRequestException(
            this.getMessage()
        );
    }
}
