package com.alaaeldin.saasmultitenantapplication.multitenant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TenantContext {

    private static final Logger log = LoggerFactory.getLogger(TenantContext.class);
    private static final ThreadLocal<String> TENANT_ID = new ThreadLocal<>();

    private TenantContext() {
        /* This utility class should not be instantiated */
    }

    public static void setTenantId(String tenantId) {
        log.debug("Setting tenant id to {}", tenantId);
        TENANT_ID.set(tenantId);
    }

    public static String getTenantId() {
        return TENANT_ID.get();
    }

    public static void clear() {
        TENANT_ID.remove();
    }
}
