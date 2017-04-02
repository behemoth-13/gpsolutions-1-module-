package com.gpsolutions.rest.domain;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * Created by Alex on 01.04.2017.
 */
public class Reservation  implements Serializable {
    private int id;
    private Show show;
    private Set<Place> places;

    public Reservation() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Show getShow() {
        return show;
    }

    public void setShow(Show show) {
        this.show = show;
    }

    public Set<Place> getPlaces() {
        return places;
    }

    public void setPlaces(Set<Place> places) {
        this.places = places;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Reservation)) return false;

        Reservation that = (Reservation) o;

        if (getId() != that.getId()) return false;
        if (getShow() != null ? !getShow().equals(that.getShow()) : that.getShow() != null) return false;
        return getPlaces() != null ? getPlaces().equals(that.getPlaces()) : that.getPlaces() == null;
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + (getShow() != null ? getShow().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", show=" + show +
                ", places=" + places +
                '}';
    }
}
