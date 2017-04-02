package com.gpsolutions.rest.service;

import com.gpsolutions.rest.domain.Reservation;

import java.io.IOException;

/**
 * Created by Alex on 02.04.2017.
 */
public interface ReservationService {
    Reservation addReservation(Reservation reservation);
    void cancelReservation(int id) throws IOException;
    Reservation getReservationById(int id);
}
