package ht.demo.util.strategy;

import org.springframework.stereotype.Component;

@Component
public class TaiwanGreeting implements GreetingStrategy {

    @Override
    public String sayHelloTo(String user) {
        return "哩齁!" + user;
    }

    @Override
    public From getLocation() {
        return From.Taiwan;
    }
}
