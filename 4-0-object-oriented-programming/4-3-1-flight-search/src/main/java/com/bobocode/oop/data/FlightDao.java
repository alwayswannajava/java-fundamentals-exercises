package com.bobocode.oop.data;

import com.bobocode.oop.service.Flights;

import java.util.HashSet;
import java.util.Set;

/**
 * {@link FlightDao} represents a Data Access Object (DAO) for flights. The implementation is simplified, so it just
 * uses {@link HashSet} to store flight numbers.
 * <p>
 */
public class FlightDao implements Flights {
    private Set<String> flights = new HashSet<>();

    /**
     * Stores a new flight number
     *
     * @param flightNumber a flight number to store
     * @return {@code true} if a flight number was stored, {@code false} otherwise
     */


    @Override
    public boolean register(String flightNumber) {
        return flights.add(flightNumber);
    }

    /**
     * Returns all stored flight numbers
     *
     * @return a set of flight numbers
     */
    @Override
    public Set<String> findAll() {
        return flights;
    }

}
