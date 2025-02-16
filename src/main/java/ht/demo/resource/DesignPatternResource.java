package ht.demo.resource;

import ht.demo.dto.design.pattern.StrategyBundleDto;
import ht.demo.util.state.FsmAction;
import ht.demo.util.state.FsmFactory;
import ht.demo.util.state.FsmState;
import ht.demo.util.strategy.GreetingStrategyDispatcher;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class DesignPatternResource {

    private final GreetingStrategyDispatcher greetingStrategyDispatcher;
    private final FsmFactory fsmFactory;

    @Parameters(
        value = {
            @Parameter(
                name = "from",
                in = ParameterIn.QUERY,
                examples = {
                    @ExampleObject(
                        name = "taiwan",
                        value = "Taiwan"
                    ),
                    @ExampleObject(
                        name = "japan",
                        value = "Japan"
                    ),
                    @ExampleObject(
                        name = "star trek",
                        value = "StarTrek"
                    )
                }
            ),
            @Parameter(
                name = "user",
                in = ParameterIn.QUERY,
                example = "JoJo"
            ),
        }
    )
    @GetMapping("/api/design-patterns/strategy")
    public String strategy(
        @NonNull StrategyBundleDto strategyBundleDto
    ) {
        return greetingStrategyDispatcher.sayHello(
            strategyBundleDto.getFrom(),
            strategyBundleDto.getUser()
        );
    }

    @PostMapping("/api/design-patterns/state")
    public FsmState generateState() {
        return fsmFactory.generateFsm()
            .getState();
    }

    @PostMapping("/api/design-patterns/state:reset")
    public FsmState resetState() {
        return fsmFactory.reset()
            .getState();
    }

    @GetMapping("/api/design-patterns/state")
    public FsmState getCurrentState() {
        return fsmFactory.getCurrentState();
    }

    @PutMapping("/api/design-patterns/state:{action}")
    public FsmState takeAction(
        @PathVariable("action") FsmAction action
    ) throws BadRequestException {
        return fsmFactory.take(action);
    }
}
