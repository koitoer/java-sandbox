package com.koitoer.work.domain;

import java.math.BigDecimal;
import java.util.List;

import lombok.Data;

/**
 * Created by mmena on 4/25/18.
 */
@Data
public class Office {

    private String team;

    private List<Person> people;

    private Cost cost;

    private List<Pet> pets;
}
