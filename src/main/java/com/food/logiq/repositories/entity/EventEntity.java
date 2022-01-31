package com.food.logiq.repositories.entity;

import com.food.logiq.repositories.enums.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.List;

/**
 * EventEntity for managing events.
 * Annotated with {@link Data @Data}, {@link Builder @Builder}, {@link NoArgsConstructor @NoArgsConstructor},
 * {@link AllArgsConstructor @AllArgsConstructor}, {@link Entity @Entity}, and {@link Table @Table}.
 *
 * @author Rotation5
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode()
@Entity
@Table(name = "event")
public class EventEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "event_type")
    @Enumerated(EnumType.STRING)
    private EventType type;

    @Column(name = "is_deleted", nullable = false, columnDefinition = "tinyint(1) default 0")
    private boolean isDeleted;

    @Column(name = "created_at", updatable = false)
    @CreationTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
    private OffsetDateTime createdAt;

    @Column(name = "created_by", updatable = false)
    public String createdBy;

    @Column(name = "updated_at")
    @UpdateTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
    OffsetDateTime updatedAt;

    @Column(name = "updated_by")
    public String updatedBy;

    @JsonManagedReference("eventEntity")
    @OneToMany(mappedBy = "eventEntity")
    private List<ContentEntity> contents;
}
