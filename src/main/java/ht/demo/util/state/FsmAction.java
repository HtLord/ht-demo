package ht.demo.util.state;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Set;

@Getter
@AllArgsConstructor
public enum FsmAction {
    Waiting(
        Set.of(
            FsmState.Proposal
        ),
        FsmState.Pending
    ),
    Serving(
        Set.of(
            FsmState.Pending
        ),
        FsmState.OnGoing
    ),
    SettleDown(
        Set.of(
            FsmState.OnGoing
        ),
        FsmState.Finish
    ),
    Cancel(
        Set.of(
            FsmState.Pending
        ),
        FsmState.Cancelled
    ),
    ;

    private final Set<FsmState> prevs;
    private final FsmState next;
}
