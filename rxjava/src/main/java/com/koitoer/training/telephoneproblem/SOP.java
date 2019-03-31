package com.koitoer.training.telephoneproblem;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Test;

import com.google.common.collect.ImmutableMap;

import static org.hamcrest.Matchers.is;

/**
 * Created by mmena on 4/12/18.
 */
public class SOP {

    @Test
    public void sortedEntriesTest() {

        Map<String, AtomicInteger> a = ImmutableMap.of("a", new AtomicInteger(1));
        List<Map.Entry<String, AtomicInteger>> expectedList = a.entrySet().stream().collect(Collectors.toList());
        Map<String, AtomicInteger> b = ImmutableMap.of("a", new AtomicInteger(1));
        List<Map.Entry<String, AtomicInteger>> actualList = b.entrySet().stream().collect(Collectors.toList());

        Assertions.assertThat(expectedList.get(0)).isEqualTo(actualList.get(0));
        Assert.assertThat(expectedList, is(actualList));

    }

}
