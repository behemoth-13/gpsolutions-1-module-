package com.gpsolutions.rest.domain;

/**
 * Created by Alex on 01.04.2017.
 */
public class Place {
    private int seat;
    private int row;

    public Place() {
    }

    public int getSeat() {
        return seat;
    }

    public void setSeat(int seat) {
        this.seat = seat;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Place)) return false;

        Place place = (Place) o;

        return getSeat() == place.getSeat() && getRow() == place.getRow();
    }

    @Override
    public int hashCode() {
        int result = getSeat();
        result = 31 * result + getRow();
        return result;
    }

    @Override
    public String toString() {
        return "Place{" +
                "seat=" + seat +
                ", row=" + row +
                '}';
    }
}
