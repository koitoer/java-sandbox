package com.koitoer.work.domain;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by mmena on 4/25/18.
 */
@AllArgsConstructor
@Data
public class Pet {

    private PetType petType;

    private String name;

    private BigDecimal value;

}
