package com.koitoer.work;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Before;
import org.junit.Test;

import com.koitoer.work.domain.Cost;
import com.koitoer.work.domain.Office;
import com.koitoer.work.domain.Person;
import com.koitoer.work.domain.PersonType;
import com.koitoer.work.domain.Pet;
import com.koitoer.work.domain.PetType;

/**
 * Created by mmena on 4/25/18.
 */
public class Launcher {

    private Office office;

    @Before
    public void init(){
        office = new Office();
        office.setCost(new Cost(BigDecimal.ONE, "rdd"));
        office.setPeople(Arrays.asList(new Person(BigDecimal.ONE, PersonType.ENGINEER),
            new Person(BigDecimal.ONE, PersonType.ENGINEER),
            new Person(BigDecimal.ONE, PersonType.ENGINEER),
            new Person(BigDecimal.TEN, PersonType.PROJECT_MANAGER),
            new Person(BigDecimal.TEN, PersonType.PROJECT_MANAGER)));
        office.setPets(Arrays.asList(new Pet(PetType.CAT, "miau", BigDecimal.ONE),
            new Pet(PetType.DOG, "wow", BigDecimal.ONE),
            new Pet(PetType.CAT, "mini", BigDecimal.TEN)));

    }

    @Test
    public void test(){
        Map map = office.getPeople().stream().collect(Collectors.groupingBy(Person::getPersonType));

        //Extract people who are engineers and pets with name wow, also add cost for rdd
        ListItemPredicate<Office> officeListItemPredicate = new ListItemPredicate<>("rdd", new Predicate<Office>() {

            @Override public boolean test(Office office) {
                return false;
            }
        });
    }


    @Test
    public void verifyStreams(){
        Stream<Integer> a = Stream.of(1, 2, 4);

        Integer totalAmount = a.map(x -> x).reduce((x, y) -> x + y ).get();

        a.collect(Collectors.toList());



    }
}
