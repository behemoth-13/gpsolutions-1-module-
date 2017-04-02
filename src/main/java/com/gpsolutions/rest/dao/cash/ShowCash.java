package com.gpsolutions.rest.dao.cash;

import com.gpsolutions.rest.dao.ShowDao;
import com.gpsolutions.rest.domain.Show;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * Created by Alex on 01.04.2017.
 */
public class ShowCash {
    private static Set<Show> cash = new CopyOnWriteArraySet<Show>();

    private static final ShowCash instance = new ShowCash();

    public static ShowCash getInstance() {
        return instance;
    }

    private ShowCash() {
        cash = ShowDao.getInstance().getShows();
    }

    public boolean isExist(Show show) {
        return cash.contains(show);
    }
}