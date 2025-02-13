package ht.demo.error;

import lombok.Getter;

@Getter
public enum CoinCodedError implements CodedError {
    CE_0001("Can not found coin by name"),
    ;

    private final String message;
    private final String messageFmt;

    CoinCodedError(String message) {
        this.message = message;
        this.messageFmt = message;
    }

    public String getCode() {
        return this.name();
    }
}
