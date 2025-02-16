package ht.demo.util.state;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum FsmState {
    Proposal,
    Pending,
    OnGoing,
    Finish,
    Cancelled,
    ;
}
