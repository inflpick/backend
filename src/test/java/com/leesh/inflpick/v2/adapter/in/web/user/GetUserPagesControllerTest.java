//package com.leesh.inflpick.v2.adapter.in.web.user;
//
//import com.leesh.inflpick.v2.adapter.out.persistence.mongo.user.FakeUserRepository;
//import com.leesh.inflpick.v3.shared.application.dto.OffsetPage;
//import com.leesh.inflpick.v3.shared.application.dto.OffsetPageQuery;
//import com.leesh.inflpick.v3.user.adapter.in.web.QueryUserController;
//import com.leesh.inflpick.v3.shared.adapter.in.web.WebOffsetPageResponse;
//import com.leesh.inflpick.v3.user.application.port.in.QueryUserUseCase;
//import com.leesh.inflpick.v3.user.application.port.in.exception.UserNotFoundException;
//import com.leesh.inflpick.v3.user.domain.User;
//import com.leesh.inflpick.v3.user.domain.vo.AuthenticationCode;
//import com.leesh.inflpick.v3.user.domain.vo.Oauth2Info;
//import com.leesh.inflpick.v3.user.domain.vo.UserId;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.http.HttpStatusCode;
//import org.springframework.http.ResponseEntity;
//
//import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
//
//@DisplayName("어댑터 테스트 : UserController")
//class GetUserPagesControllerTest {
//
//    QueryUserController sut;
//
//    @DisplayName("유저 페이지 조회 테스트")
//    @Test
//    void getUserPage() {
//
//        // given
//        Integer page = 0;
//        Integer size = 20;
//        String[] sort = {"createdDate,asc"};
//
//        // when
//        FakeUserRepository repository = FakeUserRepository.create();
//        QueryUserUseCase service = new QueryUserUseCase() {
//            @Override
//            public User query(UserId userId) throws UserNotFoundException {
//                return null;
//            }
//
//            @Override
//            public User query(AuthenticationCode authenticationCode) throws UserNotFoundException {
//                return null;
//            }
//
//            @Override
//            public User query(Oauth2Info oauth2Info) throws UserNotFoundException {
//                return null;
//            }
//
//            @Override
//            public OffsetPage<User> query(OffsetPageQuery query) {
//                return null;
//            }
//        };
//        sut = new QueryUserController(service);
//        ResponseEntity<WebOffsetPageResponse<UserWebResponse>> response = sut.query(page, size, sort);
//
//        // then
//        assertThat(response.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(200));
//        assertThat(response.getBody()).isNotNull();
//        UserWebResponse[] contents = response.getBody().getContents();
//        assertThat(contents).isNotNull();
//        assertThat(contents.length).isEqualTo(20);
//    }
//}