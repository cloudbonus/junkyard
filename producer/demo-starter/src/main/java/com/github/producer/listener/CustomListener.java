package com.github.producer.listener;

import com.github.producer.config.StarterCustomProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;


/**
 * @author Raman Haurylau
 */
public class CustomListener implements ApplicationListener<ContextRefreshedEvent> {

    private final Logger log = LoggerFactory.getLogger(CustomListener.class);
    private final StarterCustomProperties props;

    public CustomListener(StarterCustomProperties props) {
        this.props = props;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        log.info("Demo starter booted and ready with the name: {}", props.getName());
    }
}
