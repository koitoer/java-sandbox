package com.koitoer.work;

import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

import lombok.val;

public class ListItemCategorizer {

    /**
     * Categorizes each item with the label from the first matching predicate
     */
    public static <T> Multimap<String, T> categorizeItems(List<T> items, List<ListItemPredicate<T>> predicates, String defaultCategory) {
        val categorizedItems = HashMultimap.<String, T>create();
        for (val item : items) {
            val label = findFirstMatchingLabel(item, predicates, defaultCategory);
            categorizedItems.put(label, item);
        }
        return categorizedItems;
    }


    /**
     * Categorizes each item with the label from the first matching predicate
     */
    public static <T> T categorizeItem(List<T> items, ListItemPredicate<T> predicate) {
        val categorizedItems = new ArrayList<T>();
        for (val item : items) {
            if(predicate.getPredicate().test(item))
                categorizedItems.add(item);
        }
        return categorizedItems.get(0);
    }


    private static <T> String findFirstMatchingLabel(T item, List<ListItemPredicate<T>> predicates, String defaultCategory) {
        for (val predicate : predicates) {
            if (predicate.getPredicate().test(item)) {
                return predicate.getLabel();
            }
        }
        return defaultCategory;
    }

}
