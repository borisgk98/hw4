package space.borisgk.itis.sem4.hw4.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class YAMLMessageSourceConfig {

    @Bean
    public String yamlDirPath() {
        return "yamlres";
    }
}
