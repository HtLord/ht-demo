package ht.demo.resource;

import ht.demo.error.CustomizedException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomizeExceptionResource {

    @GetMapping("/api/exceptions")
    public String throwException() throws CustomizedException {
        if (true) {
            throw new CustomizedException(
                "ERR_0001",
                "You are executing a endpoint which will always return 400."
            );
        }

        return ":(";
    }
}
