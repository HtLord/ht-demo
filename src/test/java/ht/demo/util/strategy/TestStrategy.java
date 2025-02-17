package ht.demo.util.strategy;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestStrategy {

    @Autowired
    GreetingStrategyDispatcher greetingStrategyDispatcher;

    @Test
    void testDefaultStrategy() {
        Assertions.assertEquals(
            "Okay",
            greetingStrategyDispatcher.sayHello(
                From.Default,
                "Weichen"
            )
        );
    }

    @Test
    void testTaiwanStrategy() {
        Assertions.assertEquals(
            "哩齁!Weichen",
            greetingStrategyDispatcher.sayHello(
                From.Taiwan,
                "Weichen"
            )
        );
    }

    @Test
    void testJapanStrategy() {
        Assertions.assertEquals(
            "オラオラオラオラオラオラ!Weichenオラ!",
            greetingStrategyDispatcher.sayHello(
                From.Japan,
                "Weichen"
            )
        );
    }

    @Test
    void testStarTrekStrategy() {
        Assertions.assertEquals(
            "Live long and prosper",
            greetingStrategyDispatcher.sayHello(
                From.StarTrek,
                "Weichen"
            )
        );
    }

}
