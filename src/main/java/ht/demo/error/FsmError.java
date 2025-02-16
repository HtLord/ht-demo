package ht.demo.error;

import lombok.Getter;

@Getter
public enum FsmError implements CodedError {
    FSM_0001("Invalidate action"),
    FSM_0002("End of fsm"),
    FSM_0003("Only allow 1 fsm working, remove previous fsm first"),
    ;

    private final String message;
    private final String messageFmt;

    FsmError(String message) {
        this.message = message;
        this.messageFmt = message;
    }

    public String getCode() {
        return this.name();
    }
}
