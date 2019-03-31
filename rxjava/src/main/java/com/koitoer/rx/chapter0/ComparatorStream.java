package com.koitoer.rx.chapter0;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.google.common.collect.ImmutableMap;

/**
 * Created by mmena on 11/27/17.
 */
public class ComparatorStream {

    public static void main(String[] args) {
        ImmutableMap<String, BigDecimal> a = ImmutableMap.of("1", BigDecimal.ONE);
        ImmutableMap<String, BigDecimal> bb = ImmutableMap.of("1", BigDecimal.ONE, "2", BigDecimal.TEN);
        ImmutableMap<String, BigDecimal> b = ImmutableMap.of("1", BigDecimal.ONE, "2", BigDecimal.TEN);
        ImmutableMap<String, BigDecimal> c = ImmutableMap.of("1", BigDecimal.ONE, "2", BigDecimal.TEN , "3", BigDecimal.TEN);
        ImmutableMap<String, BigDecimal> aa = ImmutableMap.of();

        List<ImmutableMap> list = Arrays.asList(bb, b, a, c, aa);


        ImmutableMap minMap = list.stream().collect(Collectors.minBy((o1, o2) -> o1.size() - o2.size())).get();

        System.out.println(minMap);
    }


}
