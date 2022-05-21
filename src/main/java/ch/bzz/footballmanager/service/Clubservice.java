package ch.bzz.footballmanager.service;

import ch.bzz.footballmanager.data.DataHandler;
import ch.bzz.footballmanager.model.Club;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("club")
public class Clubservice {
    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listClubs() {
        List<Club> clubList = DataHandler.getInstance().readAllClubs();
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
        Club club = DataHandler.getInstance().readClubByUUID(clubUUID);
        if(club == null){
            httpStatus = 410;
        }
        return Response
                .status(httpStatus)
                .entity(club)
                .build();
    }
}