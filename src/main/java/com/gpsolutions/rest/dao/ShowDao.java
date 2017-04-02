package com.gpsolutions.rest.dao;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gpsolutions.rest.domain.Show;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Created by Alex on 01.04.2017.
 */
public class ShowDao {

    private static ShowDao instance = new ShowDao();

    public static ShowDao getInstance() {
        return instance;
    }

    private ShowDao () {}

    private final static String BASE_FILE = "e:\\schedule.json";

    public Set<Show> getShows() {

        ObjectMapper mapper = new ObjectMapper();
        Set<Show> shows = new HashSet<Show>();
        try {
            shows = mapper.readValue(new File(BASE_FILE), new TypeReference<Set<Show>>(){});
        } catch (IOException e) {
            e.printStackTrace();
        }
        return shows;
    }
}