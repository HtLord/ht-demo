package ht.demo.util.strategy;

import org.springframework.stereotype.Component;

@Component
public class StarTrekGreeting implements GreetingStrategy {

    @Override
    public String sayHelloTo(String user) {
        return "Live long and prosper";
    }

    @Override
    public From getLocation() {
        return From.StarTrek;
    }
}
