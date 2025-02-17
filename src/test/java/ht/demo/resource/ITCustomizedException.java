package ht.demo.resource;

import ht.demo.error.ErrorInfo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ITCustomizedException {

    @LocalServerPort
    private int port;
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void testCustomizedException() {
        var res =
            restTemplate.exchange(
                "http://localhost:" + port + "/api/exceptions",
                HttpMethod.GET,
                null,
                ErrorInfo.class
            );
        Assertions.assertEquals(
            HttpStatus.BAD_REQUEST,
            res.getStatusCode()
        );
        var err =
            res.getBody();
        Assertions.assertEquals(
            "ERR_0001",
            err.getCode()
        );
        Assertions.assertEquals(
            "/api/exceptions",
            err.getUrl()
        );
        Assertions.assertEquals(
            "You are executing a endpoint which will always return 400.",
            err.getReason()
        );
    }
}
