package com.koitoer.work.domain;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by mmena on 4/25/18.
 */
@Data
@AllArgsConstructor
public class Person {

    public BigDecimal salary;

    public PersonType personType;

}
