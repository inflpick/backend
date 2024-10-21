package com.leesh.inflpick.v2.adapter.out.persistence.mongo.user;

import com.leesh.inflpick.v2.adapter.out.uuid.UuidHolder;
import com.leesh.inflpick.v2.application.dto.common.OffsetPageQuery;
import com.leesh.inflpick.v2.application.dto.common.OffsetPage;
import com.leesh.inflpick.v2.domain.user.User;
import com.leesh.inflpick.v2.domain.user.vo.*;
import com.leesh.inflpick.v2.application.port.out.user.CommandUserPort;
import com.leesh.inflpick.v2.application.port.out.user.QueryUserPort;

import java.util.*;

public class FakeUserRepository implements CommandUserPort, QueryUserPort {

    private final UuidHolder uuidHolder;
    private final List<User> users;

    private FakeUserRepository() {
        uuidHolder = () -> UUID.randomUUID().toString();
        users = Collections.synchronizedList(new ArrayList<>());
        for (int i = 0; i < 100; i++) {
            Nickname nickname = Nickname.create("nickname" + i);
            String oauth2Id = uuidHolder.uuid();
            Oauth2Info oauth2Info = Oauth2Info.create(oauth2Id, Oauth2Provider.GOOGLE);
            User user = User.builder(nickname, oauth2Info).build();
            users.add(user);
        }
    }

    @Override
    public UserId save(User user) {
        if (user.isPersisted()) {
            users.remove(user);
            users.add(user);
        } else {
            UserId id = UserId.create(uuidHolder.uuid());
            User newUser = User.withId(id, user);
            users.add(newUser);
        }
        return user.getId();
    }

    @Override
    public Optional<User> query(Oauth2Info oauth2Info) {
        return users.stream()
                .filter(user -> user.getOauth2Info().equals(oauth2Info))
                .findFirst();
    }

    @Override
    public Optional<User> query(UserId userId) {
        return users.stream()
                .filter(user -> user.getId().equals(userId))
                .findFirst();
    }

    @Override
    public Optional<User> query(AuthenticationCode authenticationCode) {
        return users.stream()
                .filter(user -> user.getAuthenticationProcess().getCode().equals(authenticationCode))
                .findFirst();
    }

    @Override
    public OffsetPage<User> query(OffsetPageQuery request) {
        users.sort(Comparator.comparing(User::getCreatedDate).reversed());
        Collection<User> contents = users.subList(request.getPage(), request.getPage() + request.getSize());
        Integer page = request.getPage();
        return OffsetPage.create(contents,
                page,
                users.size() / request.getSize(),
                users.size(),
                (long) users.size(),
                "");
    }

    public static FakeUserRepository create() {
        return new FakeUserRepository();
    }
}
