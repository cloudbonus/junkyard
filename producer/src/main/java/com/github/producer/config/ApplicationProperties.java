package com.github.producer.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Raman Haurylau
 */

@Getter
@ConfigurationProperties(prefix = ApplicationProperties.CONFIGURATION_PROPERTY_PREFIX, ignoreUnknownFields = false)
public class ApplicationProperties {

    static final String CONFIGURATION_PROPERTY_PREFIX = "application";
    private final Async async = new Async();

    @Setter
    @Getter
    public static class Async {
        private Integer corePoolSize;
        private Integer maxPoolSize;
        private Integer queueCapacity;
    }
}
