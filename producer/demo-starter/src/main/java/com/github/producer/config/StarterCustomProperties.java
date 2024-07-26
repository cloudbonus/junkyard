package com.github.producer.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Raman Haurylau
 */
@ConfigurationProperties(prefix = "demo-starter")
public class StarterCustomProperties {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
