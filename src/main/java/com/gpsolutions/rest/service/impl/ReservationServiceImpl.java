package com.gpsolutions.rest.service.impl;

import com.gpsolutions.rest.dao.cash.ReservationCash;
import com.gpsolutions.rest.dao.cash.ShowCash;
import com.gpsolutions.rest.domain.Place;
import com.gpsolutions.rest.domain.Reservation;
import com.gpsolutions.rest.service.ReservationService;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Set;

/**
 * Created by Alex on 01.04.2017.
 */
public class ReservationServiceImpl implements ReservationService {

    private static ShowCash showCash= ShowCash.getInstance();
    private static ReservationCash reservationCash = ReservationCash.getInstance();


    private static ReservationServiceImpl instance = new ReservationServiceImpl();

    public static ReservationServiceImpl getInstance() {
        return instance;
    }

    private ReservationServiceImpl() {}



    public Reservation addReservation(Reservation reservation) {
        String dateString = reservation.getShow().getDate() + " " + reservation.getShow().getTime();//validation Date
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        try {
            formatter.parse(dateString);
        } catch (ParseException e) {
            throw new IllegalArgumentException("wrong date");
        }
        if (!showCash.isExist(reservation.getShow())) {                                             //validation Show
            throw new IllegalArgumentException("show does not exist in schedule");
        }
        for (Place place : reservation.getPlaces()) {                                               //validation Place
            int seat = place.getSeat();
            int row = place.getRow();
            if (seat < 1 || seat > 20 || row < 1 || row > 7) {
                throw new IllegalArgumentException("Place is not valid");
            }
        }

        Set<Place> reservPlaces = reservation.getPlaces();                                          //place isTaken
        Set<Place> taken = reservationCash.getPlaces(reservation.getShow());
        for (Place reservPlace : reservPlaces) {
            if (taken.contains(reservPlace)) {
                throw new IllegalArgumentException("Place is taken(seat: " + reservPlace.getSeat() +
                        " row: " + reservPlace.getRow());
            }
        }
        try {
            return reservationCash.addReservation(reservation);
        } catch (IOException e) {
            throw new IllegalArgumentException("saving reservation fail");
        }
    }

    public void cancelReservation(int id) throws IOException {
        reservationCash.cancelReservation(id);
    }

    public Reservation getReservationById(int id) {
        return reservationCash.getReservationById(id);
    }
}