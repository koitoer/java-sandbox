package com.koitoer.work;

import java.util.function.Predicate;

import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class ListItemPredicate<T> {

    private final String label;

    private final Predicate<T> predicate;
}
