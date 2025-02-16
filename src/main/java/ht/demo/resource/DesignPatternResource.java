package ht.demo.resource;

import ht.demo.dto.design.pattern.StrategyBundleDto;
import ht.demo.util.strategy.GreetingStrategyDispatcher;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class DesignPatternResource {

    private final GreetingStrategyDispatcher greetingStrategyDispatcher;

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
}
