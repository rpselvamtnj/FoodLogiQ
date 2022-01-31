package com.food.logiq.repositories.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * ContentEntity for managing contents for the events.
 * Annotated with {@link Data @Data}, {@link Builder @Builder}, {@link NoArgsConstructor @NoArgsConstructor},
 * {@link AllArgsConstructor @AllArgsConstructor}, {@link Entity @Entity}, and {@link Table @Table}.
 *
 * @author Rotation5
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(
        name = "content",
        uniqueConstraints = @UniqueConstraint(columnNames = {"gtin", "lot"})
)
public class ContentEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "gtin")
    private String gtin;

    @Column(name = "lot")
    public String lot;

    @Column(name = "best_by_date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    public LocalDate bestByDate;

    @Column(name = "expiration_date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    public LocalDate expirationDate;

    @JsonBackReference("contents")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id", referencedColumnName = "id", nullable = false)
    private EventEntity eventEntity;
}
