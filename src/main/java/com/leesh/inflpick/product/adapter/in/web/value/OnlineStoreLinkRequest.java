package com.leesh.inflpick.product.adapter.in.web.value;

import com.leesh.inflpick.common.v2.adapter.validator.RequiredFieldsValidator;
import com.leesh.inflpick.product.adapter.in.web.docs.OnlineStoreLinkRequestApiDocs;
import com.leesh.inflpick.product.core.domain.value.OnlineStore;
import com.leesh.inflpick.product.core.domain.value.OnlineStoreLink;
import lombok.Builder;
import org.jetbrains.annotations.Nullable;

@Builder
public record OnlineStoreLinkRequest(String platform, String url) implements OnlineStoreLinkRequestApiDocs {

    public OnlineStoreLinkRequest(@Nullable String platform,
                                  @Nullable String url) {
        RequiredFieldsValidator.validate(platform, url);
        assert platform != null;
        this.platform = platform.strip();
        assert url != null;
        this.url = url.strip();
    }

    public OnlineStoreLink toEntity() {
            OnlineStore onlineStore = OnlineStore.from(platform);
            return OnlineStoreLink.of(
                    onlineStore,
                    url);
    }
}
