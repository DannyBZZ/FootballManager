package ch.bzz.footballmanager.service;

import ch.bzz.footballmanager.data.DataHandler;
import ch.bzz.footballmanager.model.Club;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.UUID;

/**
 * clubtest service
 */
@Path("club")
public class Clubservice {

    /**
     * confirms the application runs
     *
     * @return message
     */
    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listClubs(
            @CookieParam("userRole") String userRole
    ) {
        List<Club> clubList = null;
        int httpStatus;

        if (userRole == null || userRole.equals("guest")) {
            httpStatus = 403;
        } else {
            httpStatus = 200;
            clubList = DataHandler.readAllClubs();
        }

        return Response
                .status(httpStatus)
                .entity(clubList)
                .build();
    }

    @GET
    @Path("read")
    @Produces(MediaType.APPLICATION_JSON)
    public Response readClubs(
            @QueryParam("clubUUID") String clubUUID,
            @CookieParam("userRole") String userRole
    ) {
        int httpStatus;

        Club club = null;
        if (userRole == null || userRole.equals("guest")) {
            httpStatus = 403;
        } else {
            httpStatus = 200;
            club = DataHandler.readClubByUUID(clubUUID);
        }

        if (club == null) {
            httpStatus = 410;
        }

        return Response
                .status(httpStatus)
                .entity(club)
                .build();
    }

    /**
     * inserts a new club
     *
     * @return Response
     */
    @POST
    @Path("create")
    @Produces(MediaType.TEXT_PLAIN)
    public Response insertClub(
            @Valid @BeanParam Club club,
            @CookieParam("userRole") String userRole
    ) {
        int httpStatus;
        if (userRole == null || userRole.equals("guest")) {
            httpStatus = 403;
        } else {
            httpStatus = 200;
            club.setClubUUID(UUID.randomUUID().toString());
            DataHandler.insertClub(club);
        }

        return Response
                .status(httpStatus)
                .entity("")
                .build();
    }

    /**
     * updates a new club
     *
     * @return Response
     */
    @PUT
    @Path("update")
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateClub(
            @Valid @BeanParam Club club,
            @CookieParam("userRole") String userRole
    ) {
        int httpStatus;
        Club oldclub = DataHandler.readClubByUUID(club.getClubUUID());

        if (userRole == null || userRole.equals("guest")) {
            httpStatus = 403;
        } else {
            httpStatus = 200;
            if (oldclub != null) {
                oldclub.setName(club.getName());
                oldclub.setLeague(club.getLeague());
                oldclub.setStadium(club.getStadium());
                DataHandler.updateClub();
            } else {
                httpStatus = 410;
            }
        }
        return Response
                .status(httpStatus)
                .entity("")
                .build();
    }

    /**
     * deletes a club identified by its clubUUID
     *
     * @param clubUUID the key
     * @return Response
     */
    @DELETE
    @Path("delete")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteClub(
            @QueryParam("clubUUID") String clubUUID,
            @CookieParam("userRole") String userRole
    ) {
        int httpStatus;

        if (userRole == null || userRole.equals("guest")) {
            httpStatus = 403;
        } else {
            httpStatus = 200;
            if (!DataHandler.deleteClub(clubUUID)) {
                httpStatus = 410;
            }
        }

        return Response
                .status(httpStatus)
                .entity("")
                .build();
    }
}