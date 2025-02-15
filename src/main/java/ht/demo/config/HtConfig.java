package ht.demo.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "ht")
@Getter
@Setter
public class HtConfig {

    private Service service;

    @Getter
    @Setter
    public static class Service {

        private Data data;
    }

    @Getter
    @Setter
    public static class Data {

        private String host;
        private String endpoint;
    }

}
