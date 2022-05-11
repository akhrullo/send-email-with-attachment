package uz.iDev.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "mail")
public class ProviderConfiguration {
    private String host;
    private int port;
    private String username;
    private String password;
    private Boolean debug;
}
