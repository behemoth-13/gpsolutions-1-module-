package com.gpsolutions.rest.domain;

import java.io.Serializable;

/**
 * Created by Alex on 01.04.2017.
 */
public class Show implements Serializable {

    private String date;
    private String time;
    private String film;

    public Show() {
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getFilm() {
        return film;
    }

    public void setFilm(String film) {
        this.film = film;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Show)) return false;

        Show show = (Show) o;

        if (getDate() != null ? !getDate().equals(show.getDate()) : show.getDate() != null) return false;
        if (getTime() != null ? !getTime().equals(show.getTime()) : show.getTime() != null) return false;
        return getFilm() != null ? getFilm().equals(show.getFilm()) : show.getFilm() == null;
    }

    @Override
    public int hashCode() {
        int result = getDate() != null ? getDate().hashCode() : 0;
        result = 31 * result + (getTime() != null ? getTime().hashCode() : 0);
        result = 31 * result + (getFilm() != null ? getFilm().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Show{" +
                "date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", film='" + film + '\'' +
                '}';
    }
}
