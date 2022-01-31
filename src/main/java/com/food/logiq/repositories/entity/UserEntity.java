package com.food.logiq.repositories.entity;

import lombok.*;

import javax.persistence.*;

/**
 * UserEntity for managing events.
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
@Table(name = "user")
public class UserEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "token")
    private String token;

    @Column(name = "business")
    private String business;
}
