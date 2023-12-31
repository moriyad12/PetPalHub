package com.example.PetPalHub.Security.config;

import lombok.Data;

@Data
public class CommonConfigs {
    static final String[] AUTH_WHITELIST = {
            "/proxy/**",
            "/adopter/dashboard/**",
            "/adopter/petForUser/**",

    };
    static final String[] AUTH_AUTHENTICATED = {
            "/user/**",
    };
    static final String[] AUTH_ADOPTER= {
            "/adopter/applyForPet/**",
            "/adopter/getApplications/**",

    };
    static final String[] AUTH_MASTER= {
            "/master/**"
    };
}
