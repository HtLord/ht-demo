package ht.demo.util.strategy;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class GreetingStrategyDispatcher {

    private final Map<From, GreetingStrategy> strategyDict;

    public GreetingStrategyDispatcher(
        List<GreetingStrategy> strategies
    ) {
        strategyDict =
            strategies.stream()
                .map(x ->
                    Map.entry(
                        x.getLocation(),
                        x
                    )
                )
                .collect(
                    Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue
                    )
                );
    }
    public String sayHello(
        From from,
        String user
    ) {
        if (!strategyDict.containsKey(from)) {
            return "Okay";
        }

        return strategyDict.get(from)
            .sayHelloTo(user);
    }
}
