package com.leesh.inflpick.product.adapter.in.web.value;

import com.leesh.inflpick.common.adapter.in.web.RequiredFieldsValidator;
import com.leesh.inflpick.product.core.domain.value.OnlineStoreLink;
import com.leesh.inflpick.product.core.domain.value.OnlineStoreLinks;
import com.leesh.inflpick.product.core.domain.value.ProductDescription;
import com.leesh.inflpick.product.core.domain.value.ProductName;
import com.leesh.inflpick.product.port.ProductCommand;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashSet;
import java.util.List;

@Schema(name = "제품 생성 요청", description = "제품을 생성 요청 필드")
@Builder
public record ProductRequest(
        @Schema(description = "제품명", example = "마이프로틴 “6레이어 프로틴바” (육겹바)", implementation = String.class, requiredMode = Schema.RequiredMode.REQUIRED)
        String name,
        @Schema(description = "제품 소개 글", example = "프로틴 스낵 베스트셀러 중 하나로 6겹의 뛰어난 맛과 식감을 자랑합니다.\n 1개당 20g 이상의 단백질과 탄수화물뿐만 아니라 우리 몸에 필요한 식이섬유를 풍부하게 함유하였습니다. 6 레이어 프로틴바로 맛과 영양을 모두 챙기세요!", implementation = String.class, requiredMode = Schema.RequiredMode.REQUIRED)
        String description,
        @ArraySchema(arraySchema = @Schema(description = "제품에 등록할 키워드 UUID 목록", example = "[\"92624c72-1cf2-4762-8c45-fe1a1f0a3e97\", \"8eabcaa9-5c70-46a5-a7c0-b580b1d20316\"]", implementation = String.class, requiredMode = Schema.RequiredMode.NOT_REQUIRED))
        List<String> keywordUuids,
        @ArraySchema(arraySchema = @Schema(description = "제품의 온라인 스토어 링크 목록", implementation = OnlineStoreLinkRequest.class, requiredMode = Schema.RequiredMode.NOT_REQUIRED))
        List<OnlineStoreLinkRequest> onlineStoreLinks) {

    public ProductRequest(@Nullable String name,
                          @Nullable String description,
                          @Nullable List<String> keywordUuids,
                          @Nullable List<OnlineStoreLinkRequest> onlineStoreLinks) {
        RequiredFieldsValidator.validate(name, description);
        assert name != null;
        this.name = name.strip();
        assert description != null;
        this.description = description.strip();
        this.keywordUuids = keywordUuids == null ? List.of() : keywordUuids.stream().map(String::strip).toList();
        this.onlineStoreLinks = onlineStoreLinks == null ? List.of() : onlineStoreLinks.stream()
                .map(onlineStoreLinkRequest -> new OnlineStoreLinkRequest(onlineStoreLinkRequest.platform().strip(), onlineStoreLinkRequest.url().strip()))
                .toList();
    }

    public @NotNull ProductCommand toCommand() {
        RequiredFieldsValidator.validate(name, description);
        ProductName productName = ProductName.from(name);
        ProductDescription productDescription = ProductDescription.from(description);
        return new ProductCommand(
            productName,
            productDescription,
            new HashSet<>(keywordUuids),
            convertToOnlineStoreEntity(onlineStoreLinks)
        );
    }

    public OnlineStoreLinks convertToOnlineStoreEntity(List<OnlineStoreLinkRequest> onlineStoreLinks) {
        List<OnlineStoreLink> links = onlineStoreLinks.stream()
                .map(OnlineStoreLinkRequest::toEntity)
                .toList();
        return new OnlineStoreLinks(new HashSet<>(links));
    }

}
