package com.github.producer.config;

import com.github.producer.listener.CustomListener;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Raman Haurylau
 */
@Configuration
@EnableConfigurationProperties(StarterCustomProperties.class)
public class StarterCustomConfiguration {

    @Bean
    @ConditionalOnProperty("demo-starter.name")
    public CustomListener customListener(StarterCustomProperties props) {
        return new CustomListener(props);
    }
}
