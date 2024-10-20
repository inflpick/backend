package com.leesh.inflpick.v2.adapter.in.web.user;

import com.leesh.inflpick.v2.adapter.in.web.common.dto.response.WebOffsetPageResponse;
import com.leesh.inflpick.v2.adapter.in.web.user.dto.response.UserWebResponse;
import com.leesh.inflpick.v2.appilcation.service.user.UserService;
import com.leesh.inflpick.v2.adapter.out.persistence.FakeUserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DisplayName("어댑터 테스트 : UserController")
class GetUserPagesControllerTest {

    GetUserPagesPagesController sut;

    @DisplayName("유저 페이지 조회 테스트")
    @Test
    void getUserPage() {

        // given
        Integer page = 0;
        Integer size = 20;
        String[] sort = {"createdDate,asc"};

        // when
        FakeUserRepository repository = FakeUserRepository.create();
        UserService service = UserService.builder()
                .queryUserPort(repository)
                .commandUserPort(repository)
                .build();
        sut = new GetUserPagesPagesController(service);
        ResponseEntity<WebOffsetPageResponse<UserWebResponse>> response = sut.getUserPage(page, size, sort);

        // then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(200));
        assertThat(response.getBody()).isNotNull();
        UserWebResponse[] contents = response.getBody().getContents();
        assertThat(contents).isNotNull();
        assertThat(contents.length).isEqualTo(20);
    }
}