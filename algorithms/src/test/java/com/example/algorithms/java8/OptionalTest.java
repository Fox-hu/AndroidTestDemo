package com.example.algorithms.java8;

import com.example.algorithms.java8.optional.Address;
import com.example.algorithms.java8.optional.City;
import com.example.algorithms.java8.optional.Country;

import org.junit.Test;

import java.util.Optional;

public class OptionalTest {
    @Test
    public void test() {
        Address address = null;
        final String s = Optional.ofNullable(address).map(Address :: getCountry).map(
                Country :: getCity).map(City :: getIsocode).orElse("20000");
        System.out.print("getIsocode = " + s);
    }
}