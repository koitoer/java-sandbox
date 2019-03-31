package com.koitoer.rx;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Created by mmena on 7/3/17.
 */
public class AppOptional {

    public static void main(String[] args) {

        List<Integer> aqqq = Arrays.asList(1, 2, 3, 4);
        Optional<Integer> dd = aqqq.stream().filter(x -> x > 5).findFirst();


        Optional<String> optional = Optional.of("A");
        System.out.println(optional.orElse("B"));

        optional = Optional.ofNullable(null);
        System.out.println(optional.orElse("B"));


        //Optional a = (Optional) Optional.empty().orElseThrow(() -> new RuntimeException("JAVA"));
       // System.out.println(a.get());

        List<Employee> emps = new ArrayList<>();
        emps.add(new Employee("Roy1",32));
        emps.add(new Employee("Roy2",12));
        emps.add(new Employee("Roy3",22));
        emps.add(new Employee("Roy4",42));
        emps.add(new Employee("Roy5",52));

        Integer as = emps.stream().mapToInt(e -> e.getSalary()).reduce((a,b)->Math.max(a, b)).getAsInt();
        System.out.println("Max " + as);

    }

}
