package com.alaaeldin.saasmultitenantapplication.multitenant;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class HttpTenantResolver implements TenantResolver<HttpServletRequest>{

    private final TenantHttpProperties tenantHttpProperties;

    @Override
    public String resolveTenant(@NonNull HttpServletRequest request) {
        return request.getHeader(tenantHttpProperties.getHeader());
    }
}
