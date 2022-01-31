package com.food.logiq.repositories.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * GtinLotUniqueKey for composite unique key for content.
 * Annotated with {@link Data @Data},{@link NoArgsConstructor @NoArgsConstructor},
 *
 * @author Rotation5
 */
@Data
@NoArgsConstructor
public class GtinLotUniqueKey implements Serializable {

    private String gtin;
    private String lot;
}
