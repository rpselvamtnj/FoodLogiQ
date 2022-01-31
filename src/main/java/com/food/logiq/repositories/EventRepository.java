package com.food.logiq.repositories;

import com.food.logiq.repositories.entity.EventEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * Event repository interface for retrieve events info from table.
 * Annotated with {@link Repository @Repository},
 *
 * @author Rotation5
 */
@Repository
public interface EventRepository extends JpaRepository<EventEntity, Long> {


    Optional<EventEntity> findEventEntityByIdAndCreatedByAndIsDeleted(long id, String createdBy, boolean isDeleted);

    Optional<EventEntity> findEventEntityByIdAndCreatedBy(long id, String createdBy);

    List<EventEntity> findEventEntityByCreatedByAndIsDeletedOrderByCreatedAtDesc(String createdBy, boolean isDeleted);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("UPDATE EventEntity e set e.isDeleted =1 where e.id =:eventId")
    Integer deleteEventByEventId(@Param("eventId") long eventId);
}
