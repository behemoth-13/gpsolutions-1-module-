package com.gpsolutions.rest.service.impl;

import com.gpsolutions.rest.dao.ShowDao;
import com.gpsolutions.rest.domain.Show;
import com.gpsolutions.rest.service.ShowService;

import java.util.Set;

/**
 * Created by Alex on 01.04.2017.
 */
public class ShowServiceImpl implements ShowService{
    private static ShowServiceImpl instance = new ShowServiceImpl();

    public static ShowServiceImpl getInstance() {
        return instance;
    }

    private ShowServiceImpl() {}

    public Set<Show> getShows () {
        return ShowDao.getInstance().getShows();
    }
}