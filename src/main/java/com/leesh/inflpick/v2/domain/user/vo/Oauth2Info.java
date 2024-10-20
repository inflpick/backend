package com.leesh.inflpick.v2.domain.user.vo;

import lombok.Getter;

import java.util.Objects;

@Getter
public final class Oauth2Info {

    private final String id;
    @Getter
    private final Oauth2Provider provider;

    private Oauth2Info(String id, Oauth2Provider provider) {
        this.id = id;
        this.provider = provider;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Oauth2Info) obj;
        return Objects.equals(this.id, that.id) &&
                Objects.equals(this.provider, that.provider);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, provider);
    }

    public static Oauth2Info create(String id, Oauth2Provider oauth2Provider) {
        return new Oauth2Info(id, oauth2Provider);
    }

}
