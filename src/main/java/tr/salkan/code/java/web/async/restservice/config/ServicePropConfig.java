package tr.salkan.code.java.web.async.restservice.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.io.Serializable;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties("service1")
public class ServicePropConfig implements Serializable {

    @Value("${service1.value1}")
    private @Getter @Setter String value1;
}
