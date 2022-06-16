package ch.bzz.footballmanager.service;

import ch.bzz.footballmanager.data.DataHandler;
import ch.bzz.footballmanager.model.Club;
import ch.bzz.footballmanager.model.Squad;

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
     * @return  message
     */
    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listClubs() {
        List<Club> clubList = DataHandler.readAllClubs();
        return Response
                .status(200)
                .entity(clubList)
                .build();
    }

    @GET
    @Path("read")
    @Produces(MediaType.APPLICATION_JSON)
    public Response readClubs(@QueryParam("clubUUID") String clubUUID) {
        int httpStatus = 200;
        Club club = DataHandler.readClubByUUID(clubUUID);
        if(club == null){
            httpStatus = 410;
        }
        return Response
                .status(httpStatus)
                .entity(club)
                .build();
    }

    /**
     * inserts a new club
     * @param name the name of the club
     * @param league the league of the club
     * @param stadium the stadium of the club
     * @return Response
     */
    @POST
    @Path("create")
    @Produces(MediaType.TEXT_PLAIN)
    public Response insertClub(
            @FormParam("name") String name,
            @FormParam("league") String league,
            @FormParam("stadium") String stadium
    ) {
        Club club = new Club();
        club.setClubUUID(UUID.randomUUID().toString());
        setAttributes(
                club,
                name,
                league,
                stadium
        );

        DataHandler.insertClub(club);
        return Response
                .status(200)
                .entity("")
                .build();
    }

    /**
     * updates a new club
     * @param clubUUID the key
     * @param name the name of the club
     * @param league the league of the club
     * @param stadium the stadium of the club
     * @return Response
     */
    @PUT
    @Path("update")
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateClub(
            @FormParam("clubUUID") String clubUUID,
            @FormParam("name") String name,
            @FormParam("league") String league,
            @FormParam("stadium") String stadium
    ) {
        int httpStatus = 200;
        Club club = DataHandler.readClubByUUID(clubUUID);
        if (club != null) {
            setAttributes(
                    club,
                    name,
                    league,
                    stadium
            );

            DataHandler.updateClub();
        } else {
            httpStatus = 410;
        }
        return Response
                .status(httpStatus)
                .entity("")
                .build();
    }

    /**
     * deletes a club identified by its clubUUID
     * @param clubUUID  the key
     * @return  Response
     */
    @DELETE
    @Path("delete")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteClub(
            @QueryParam("clubUUID") String clubUUID
    ) {
        int httpStatus = 200;
        if (!DataHandler.deleteClub(clubUUID)) {
            httpStatus = 410;
        }
        return Response
                .status(httpStatus)
                .entity("")
                .build();
    }

    /**
     * sets the attributes for the club-object
     * @param club the club-object
     * @param name the name of the club
     * @param league the league of the club
     * @param stadium the stadium of the club
     */
    private void setAttributes(
            Club club,
            String name,
            String league,
            String stadium
    ) {
        club.setName(name);
        club.setLeague(league);
        club.setStadium(stadium);
    }
}