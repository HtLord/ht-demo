package ht.demo.error;

import lombok.Getter;

@Getter
public enum CoinError implements Error {
    CE_0001("Can not found coin by name"),
    ;

    private final String message;
    private final String messageFmt;

    CoinError(String message) {
        this.message = message;
        this.messageFmt = message;
    }
}
