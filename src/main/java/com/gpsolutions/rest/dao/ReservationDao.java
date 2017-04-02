package com.gpsolutions.rest.dao;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gpsolutions.rest.domain.Reservation;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Alex on 01.04.2017.
 */
public class ReservationDao {
    private static ReservationDao instance = new ReservationDao();

    public static ReservationDao getInstance() {
        return instance;
    }

    private ReservationDao () {}

    private final static String BASE_FILE = "e:\\reservations.json";

    public Set<Reservation> getReservations() {
        ObjectMapper mapper = new ObjectMapper();
        Set<Reservation> reservations = new HashSet<Reservation>();
        try {
            reservations = mapper.readValue(new File(BASE_FILE), new TypeReference<Set<Reservation>>(){});
        } catch (IOException e) {
            e.printStackTrace();
        }
        return reservations;
    }

    public void save(Set<Reservation> cash) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(new File(BASE_FILE), cash);
    }
}