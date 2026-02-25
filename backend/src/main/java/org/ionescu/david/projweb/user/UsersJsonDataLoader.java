package org.ionescu.david.projweb.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.asm.TypeReference;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;

@Component
public class UsersJsonDataLoader implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(UsersJsonDataLoader.class);
    private final UserRepository userRepository;
    private final ObjectMapper objectMapper;

    public UsersJsonDataLoader(UserRepository userRepository, ObjectMapper objectMapper) {
        this.userRepository = userRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public void run(String... args) throws RuntimeException {
        if (userRepository.count() == 0) {
            log.info("Loading users from JSON file");
            try (InputStream inputStream = TypeReference.class.getResourceAsStream("/data/users.json")) {
                Users users = objectMapper.readValue(inputStream, Users.class);
                userRepository.saveAll(users.users());
            } catch (IOException ioex) {
                throw new RuntimeException("Failed to load users from JSON file", ioex);
            }
        } else {
            log.info("Users already loaded");
        }
    }
}
