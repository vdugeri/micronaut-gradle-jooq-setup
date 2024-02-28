package com.example;

import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.TestInstance;

/**
 * Basic test interface. Does not include Testcontainers. Use this interface when you're not interacting with
 * Postgres or Redis
 * and/or those dependencies are fully mocked. This is useful for stateless functions or mocked outbound requests.
 */
@MicronautTest(transactional = false, environments = {"test"})
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public interface UnitTest {

}
