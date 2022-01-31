package com.food.logiq.restapi.users;

import com.food.logiq.repositories.UserRepository;
import com.food.logiq.repositories.entity.UserEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;

/**
 * Service for managing users and token information.
 * Annotated with {@link Service @Service}, and {@link Slf4j @Slf4j},{@link Component @Component}.
 *
 * @author Rotation5
 */
@Slf4j
@Service
@Component
public class UserService {

    /**
     * Instance variables
     */
    private final UserRepository userRepository;

    /**
     * Create a new {@code UserService} with the given parameters class instance.
     *
     * @param userRepository the {@link UserRepository @UserRepository} class instance
     */
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Method is used to check valid user based on the userId and token.
     *
     * @param userId the {@link String @String}.
     * @param token  the {@link String @String}.
     * @return the {@link Boolean @Boolean}.
     */
    public boolean isValidUser(String userId, String token) {
        return userRepository.existsByUserIdAndToken(userId, token);
    }

    /**
     * Method is used to load user data into database.
     * Annotated with {@link PostConstruct @PostConstruct}
     */
    @PostConstruct
    private void postConstruct() {
        UserEntity acmeUser = UserEntity.builder().userId("12345").business("Acme").token("74edf612f393b4eb01fbc2c29dd96671").build();
        UserEntity ajaxUser = UserEntity.builder().userId("98765").business("Ajax").token("d88b4b1e77c70ba780b56032db1c259b").build();
        List<UserEntity> userEntityList = Arrays.asList(acmeUser, ajaxUser);
        userEntityList.stream().forEach(userEntity -> {
            if (!userRepository.existsByUserIdAndToken(userEntity.getUserId(), userEntity.getToken())) {
                userRepository.save(userEntity);
            }
        });
    }
}
