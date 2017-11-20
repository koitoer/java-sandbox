package com.koitoer.rx;

/**
 * Created by mmena on 11/12/17.
 */
public class Employee {

    int salary;
    String roy;

    public Employee(String roy1, int i) {
        this.roy = roy;
        this.salary = i;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public String getRoy() {
        return roy;
    }

    public void setRoy(String roy) {
        this.roy = roy;
    }
}
