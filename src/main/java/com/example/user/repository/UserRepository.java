package com.example.user.repository;

import static com.example.Tables.USERS;

import com.example.tables.pojos.Users;
import com.example.user.model.UserRequest;
import jakarta.inject.Singleton;
import java.time.OffsetDateTime;
import java.util.UUID;
import org.jooq.DSLContext;
import reactor.core.publisher.Mono;

@Singleton
public class UserRepository {

    private final DSLContext dslContext;

    public UserRepository(DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    public Mono<Users> createUser(UserRequest record) {
        return Mono.from(dslContext
                        .insertInto(USERS)
                        .columns(USERS.ID, USERS.FIRST_NAME, USERS.LAST_NAME, USERS.CREATED_AT)
                        .values(UUID.randomUUID(), record.firstName(), record.lastName(), OffsetDateTime.now())
                        .returningResult(USERS))
                .map(result -> result.into(Users.class));
    }
}
