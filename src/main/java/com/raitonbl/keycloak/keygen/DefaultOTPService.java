package com.raitonbl.keycloak.keygen;

import org.apache.commons.lang3.RandomStringUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

public class DefaultOTPService implements OTPService {

    private final List<TokenInfo> datasource = new CopyOnWriteArrayList<>();

    @Override
    public Optional<TokenInfo> get(String tokenID, String type) {

        if (tokenID == null || type == null) {
            return Optional.empty();
        }

        return datasource.stream().filter(each -> each.getId().equals(tokenID) && each.getType().equals(type)).findFirst().map(TokenInfo::copy);
    }

    @Override
    public void invalidate(String id, String type, Cause cause) {

        if (id == null || type == null || cause == null) {
            return;
        }

        datasource.removeIf(each -> each.getId().equals(id) && each.getType().equals(type));
    }

    @Override
    public void registerAttempt(String id, String type) {
        datasource.stream().filter(each -> each.getId().equals(id)).findFirst()
                .ifPresent(each -> each.setAttemptCount(each.getAttemptCount() + 1));
    }

    @Override
    public TokenInfo create(String userID, String type, long expiresIn) throws TokenException {
        try {
            TokenInfo instance = new TokenInfo();

            instance.setType(type);
            instance.setUserID(userID);
            instance.setAttemptCount(0);
            instance.setExpiresIn(expiresIn);
            instance.setCreatedAt(LocalDateTime.now());
            instance.setId(UUID.randomUUID().toString());
            instance.setCode(RandomStringUtils.randomNumeric(6, 6));

            datasource.add(instance);

            return instance;
        } catch (Exception ex) {
            throw new TokenException(ex);
        }
    }

    @Override
    public void close() {
        // DO NOTHING
    }

}

