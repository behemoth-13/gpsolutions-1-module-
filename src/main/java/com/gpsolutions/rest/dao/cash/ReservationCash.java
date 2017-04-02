package com.gpsolutions.rest.dao.cash;

import com.gpsolutions.rest.dao.ReservationDao;
import com.gpsolutions.rest.domain.Place;
import com.gpsolutions.rest.domain.Reservation;
import com.gpsolutions.rest.domain.Show;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Alex on 31.03.2017.
 */
public class ReservationCash {

    private static Set<Reservation> cash = new CopyOnWriteArraySet<Reservation>();
    private final static AtomicInteger ID = new AtomicInteger();

    private static final ReservationCash instance = new ReservationCash();

    public static ReservationCash getInstance() {
        return instance;
    }

    private ReservationCash() {
        cash = ReservationDao.getInstance().getReservations();
        int maxId = 0;
        for (Reservation reservation : cash) {
            if (reservation.getId() > maxId) {
                maxId = reservation.getId();
            }
        }
        ID.set(maxId);
    }

    public synchronized Reservation addReservation(Reservation reservation) throws IOException {
        reservation.setId(ID.incrementAndGet());
        cash.add(reservation);
        try {
            ReservationDao.getInstance().save(cash);
            return reservation;
        } catch (IOException e) {
            cash.remove(reservation);
            ID.decrementAndGet();
            throw new IOException("file does not writes");
        }
    }

    public Set<Place> getPlaces(Show show) {
        Set<Place> taken = new HashSet<Place>();
        for (Reservation reservation : cash) {
            if (show.equals(reservation.getShow())) {
                taken.addAll(reservation.getPlaces());
            }
        }
        return taken;
    }

    public synchronized void cancelReservation(int id) throws IOException {
        boolean isExist = false;
        for (Reservation reservation : cash) {
            if (reservation.getId() == id) {
                cash.remove(reservation);
                try {
                    ReservationDao.getInstance().save(cash);
                    isExist = true;
                } catch (IOException e) {
                    cash.add(reservation);
                    throw new IOException("file does not writes");
                }
            }
        }
        if (!isExist) {
            throw new IllegalArgumentException("reservation " + id + " not founded");
        }
    }

    public Reservation getReservationById(int id) {
        Reservation result = null;
        for (Reservation reservation : cash) {
            if (reservation.getId() == id) {
                result = reservation;
            }
        }
        if (result == null) {
            throw new IllegalArgumentException("reservation " + id + " not founded");
        }
        return result;
    }
}
