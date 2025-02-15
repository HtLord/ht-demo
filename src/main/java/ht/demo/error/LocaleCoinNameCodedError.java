package ht.demo.error;

import lombok.Getter;

@Getter
public enum LocaleCoinNameCodedError implements CodedError {
    LCN_0001("Can not found locale-coin-name by id"),
    ;

    private final String message;
    private final String messageFmt;

    LocaleCoinNameCodedError(String message) {
        this.message = message;
        this.messageFmt = message;
    }

    public String getCode() {
        return this.name();
    }
}
