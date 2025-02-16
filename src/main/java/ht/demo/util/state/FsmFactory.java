package ht.demo.util.state;

import ht.demo.error.FsmError;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class FsmFactory {

    private Fsm fsm;

    @Getter
    @AllArgsConstructor
    public static class Fsm {

        private FsmState state;
        private final Set<FsmState> terminals;

        public void take(
            FsmAction action
        ) throws BadRequestException {
            if (terminals.contains(state)) {
                throw FsmError.FSM_0002.generateBadRequestException();
            }
            if (!action.getPrevs()
                .contains(state)
            ) {
                throw FsmError.FSM_0001.generateBadRequestException();
            }

            state = action.getNext();
        }
    }

    // Singleton
    public Fsm generateFsm() {
        if (fsm != null) {
            return fsm;
        }

        fsm =
            new Fsm(
                FsmState.Proposal,
                Set.of(
                    FsmState.Finish,
                    FsmState.Cancelled
                )
            );
        return fsm;
    }

    public Fsm reset() {
        fsm = null;
        return this.generateFsm();
    }

    public FsmState getCurrentState() {
        if (fsm != null) {
            this.generateFsm();
        }

        assert fsm != null;
        return fsm.getState();
    }

    public FsmState take(FsmAction action) throws BadRequestException {
        if (fsm != null) {
            this.generateFsm();
        }

        assert fsm != null;
        fsm.take(action);
        return fsm.getState();
    }
}
