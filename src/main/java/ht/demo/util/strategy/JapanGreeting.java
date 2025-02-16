package ht.demo.util.strategy;

import org.springframework.stereotype.Component;

@Component
public class JapanGreeting implements GreetingStrategy {
    @Override
    public String sayHelloTo(String user) {
        return "オラオラオラオラオラオラ!" + user + "オラ!";
    }

    @Override
    public From getLocation() {
        return From.Japan;
    }
}
