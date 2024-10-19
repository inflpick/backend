package com.leesh.inflpick.user.v2.mock;

import com.leesh.inflpick.common.port.out.UuidHolder;
import com.leesh.inflpick.user.v2.core.dto.OffsetPageRequest;
import com.leesh.inflpick.user.v2.core.dto.OffsetPageResponse;
import com.leesh.inflpick.user.v2.core.entity.User;
import com.leesh.inflpick.user.v2.core.entity.vo.Nickname;
import com.leesh.inflpick.user.v2.core.entity.vo.Oauth2Info;
import com.leesh.inflpick.user.v2.core.entity.vo.Oauth2Provider;
import com.leesh.inflpick.user.v2.core.entity.vo.UserId;
import com.leesh.inflpick.user.v2.port.out.CommandUserPort;
import com.leesh.inflpick.user.v2.port.out.QueryUserPort;

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
    public User query(UserId userId) {
        return users.stream()
                .filter(user -> user.getId().equals(userId))
                .findFirst()
                .orElseThrow(NoSuchElementException::new);
    }

    @Override
    public OffsetPageResponse<User> query(OffsetPageRequest request) {
        users.sort(Comparator.comparing(User::getCreatedDate).reversed());
        Collection<User> contents = users.subList(request.getPage(), request.getPage() + request.getSize());
        Integer page = request.getPage();
        return OffsetPageResponse.create(contents,
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
