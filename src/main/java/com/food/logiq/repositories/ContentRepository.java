package com.food.logiq.repositories;

import com.food.logiq.repositories.entity.ContentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Content repository interface for retrieve content info from table.
 * Annotated with {@link Repository @Repository},
 *
 * @author Rotation5
 */
@Repository
public interface ContentRepository extends JpaRepository<ContentEntity, Long> {
}
