package com.raitonbl.keycloak.keygen;

import org.keycloak.Config;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.KeycloakSessionFactory;
import org.keycloak.provider.ServerInfoAwareProviderFactory;

import java.util.Collections;
import java.util.Map;

public class DefaultOTPServiceFactory implements OTPServiceFactory, ServerInfoAwareProviderFactory {

    private static final DefaultOTPService SINGLETON = new DefaultOTPService();

    @Override
    public OTPService create(KeycloakSession session) {
        return SINGLETON;
    }

    @Override
    public void init(Config.Scope config) {
        // DO NOTHING
    }

    @Override
    public void postInit(KeycloakSessionFactory factory) {
        // DO NOTHING
    }

    @Override
    public void close() {
        // DO NOTHING
    }

    @Override
    public String getId() {
        return "default";
    }


    @Override
    public Map<String, String> getOperationalInfo() {
        return Collections.singletonMap("name", "Default OTP Service Factory");
    }

}
