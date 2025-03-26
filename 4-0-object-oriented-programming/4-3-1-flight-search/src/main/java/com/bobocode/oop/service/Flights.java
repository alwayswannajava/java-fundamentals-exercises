package com.bobocode.oop.service;

import java.util.Set;

public interface Flights {
    boolean register(String flight);

    Set<String> findAll();
}
