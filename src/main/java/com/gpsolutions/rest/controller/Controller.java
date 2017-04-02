package com.gpsolutions.rest.controller;

import com.gpsolutions.rest.domain.*;
import com.gpsolutions.rest.service.ReservationService;
import com.gpsolutions.rest.service.ShowService;
import com.gpsolutions.rest.service.impl.ReservationServiceImpl;
import com.gpsolutions.rest.service.impl.ShowServiceImpl;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.*;


/**
 * Created by Alex on 01.04.2017.
 *
 * Read Shows         curl -H "Content-Type: application/json" -X GET http://localhost:8080/rest/
 * Create Reservation curl -H "Content-Type: application/json" -X POST -d '{"id":1,"show":{"date":"21.01.2016","time":"15:30","film":"heroes"},"places":[{"seat":16,"row":6},{"seat":15,"row":6},{"seat":10,"row":6}]}' http://localhost:8080/rest/
 * Delete Reservation curl -H "Content-Type: application/json" -X DELETE http://localhost:8080/rest/1
 * Read Reservation   curl -H "Content-Type: application/json" -X GET http://localhost:8080/rest/1
 */
@Path("/")
public class Controller {

    private static ReservationService reservationService = ReservationServiceImpl.getInstance();

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Set<Show> get() {
        ShowService service = ShowServiceImpl.getInstance();
        return service.getShows();
    }

    @POST
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Reservation create(Reservation reservation) {
        try {
            return reservationService.addReservation(reservation);
        } catch (final Exception e) {
            //noinspection Since15
            return  new Reservation() {
                public String getError() {
                    return e.getMessage();
                }
            };
        }
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") int id) {
        try {
            reservationService.cancelReservation(id);
            return Response.status(200).build(); //200 OK («хорошо»)
        } catch (Exception e) {
            return Response.status(500).build(); //500 Internal Server Error («внутренняя ошибка сервера»)
        }
    }

    @GET
    @Path("/{id}")
    @Produces("application/json")
    public Reservation get(@PathParam("id") final int id) {
        try {
            return reservationService.getReservationById(id);
        } catch (final Exception e) {
            //noinspection Since15
            return  new Reservation() {
                public String getError() {
                    return e.getMessage();
                }
            };
        }
    }
}