package com.alaaeldin.saasmultitenantapplication.multitenant;

import lombok.NonNull;

@FunctionalInterface
public interface TenantResolver<T> {

    String resolveTenant(@NonNull T object);

}
