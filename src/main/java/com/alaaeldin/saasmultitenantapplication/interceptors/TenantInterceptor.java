package com.alaaeldin.saasmultitenantapplication.interceptors;

import com.alaaeldin.saasmultitenantapplication.multitenant.HttpTenantResolver;
import com.alaaeldin.saasmultitenantapplication.multitenant.TenantContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
@RequiredArgsConstructor
public class TenantInterceptor implements HandlerInterceptor {

    private final HttpTenantResolver httpTenantResolver;

    @Override
    public boolean preHandle(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler) {
        var tenantId = httpTenantResolver.resolveTenant(request);
        TenantContext.setTenantId(tenantId);
        return true;
    }

    @Override
    public void afterCompletion(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler, @Nullable Exception ex) {
        clear();
    }

    @Override
    public void postHandle(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler, @Nullable ModelAndView modelAndView) {
        clear();
    }

    private void clear(){
        TenantContext.clear();
    }
}
