package ht.demo.util.state;

import org.apache.coyote.BadRequestException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestState {

    @Autowired
    FsmFactory factory;

    @BeforeEach
    void init() {
        factory.reset();
    }

    @Test
    void testInit() {
        var fsm =
            factory.generateFsm();
        Assertions.assertEquals(
            FsmState.Proposal,
            fsm.getState()
        );
    }

    @Test
    void testWalkToFinish() throws BadRequestException {
        var fsm =
            factory.generateFsm();
        fsm.take(FsmAction.Waiting);
        fsm.take(FsmAction.Serving);
        fsm.take(FsmAction.SettleDown);
        Assertions.assertEquals(
            FsmState.Finish,
            fsm.getState()
        );
    }

    @Test
    void testWalkToCancelled() throws BadRequestException {
        var fsm =
            factory.generateFsm();
        fsm.take(FsmAction.Waiting);
        fsm.take(FsmAction.Cancel);
        Assertions.assertEquals(
            FsmState.Cancelled,
            fsm.getState()
        );
    }

    @Test
    void testTakeInvalidateAction() throws BadRequestException {
        var fsm =
            factory.generateFsm();
        fsm.take(FsmAction.Waiting);
        Assertions.assertThrowsExactly(
            BadRequestException.class,
            () -> fsm.take(FsmAction.SettleDown)
        );
    }
}
