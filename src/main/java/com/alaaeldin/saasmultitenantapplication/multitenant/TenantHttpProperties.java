package com.alaaeldin.saasmultitenantapplication.multitenant;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "multitenancy-http")
@Component
@Getter
@Setter
public class TenantHttpProperties {

    private String header;
}
