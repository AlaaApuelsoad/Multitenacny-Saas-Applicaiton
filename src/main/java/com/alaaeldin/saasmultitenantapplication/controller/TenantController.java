package com.alaaeldin.saasmultitenantapplication.controller;

import com.alaaeldin.saasmultitenantapplication.multitenant.TenantContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tenant")
public class TenantController {

    @GetMapping
    public String getTenants() {
        return TenantContext.getTenantId();
    }
}
