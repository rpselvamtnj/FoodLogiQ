package com.food.logiq.repositories;

import com.food.logiq.repositories.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * User repository interface for retrieve users info from table.
 * Annotated with {@link Repository @Repository},
 *
 * @author Rotation5
 */
@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Boolean existsByUserIdAndToken(String userId, String token);
}
