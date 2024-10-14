package com.leesh.inflpick.keyword.port.in;

public interface KeywordCommandService {

    String create(KeywordCommand command);

    void update(String id, KeywordCommand command);

    void delete(String id);
}
