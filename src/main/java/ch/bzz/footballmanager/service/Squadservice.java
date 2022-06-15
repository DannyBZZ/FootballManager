package ch.bzz.footballmanager.service;

import ch.bzz.footballmanager.data.DataHandler;
import ch.bzz.footballmanager.model.Club;
import ch.bzz.footballmanager.model.Player;
import ch.bzz.footballmanager.model.Squad;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.UUID;

/**
 * squadtest service
 */
@Path("squad")
public class Squadservice {

    /**
     * confirms the application runs
     * @return  message
     */
    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listSquads() {
        List<Squad> squadList = DataHandler.readAllSquads();
        return Response
                .status(200)
                .entity(squadList)
                .build();
    }

    @GET
    @Path("read")
    @Produces(MediaType.APPLICATION_JSON)
    public Response readPlayers(@QueryParam("squadUUID") String squadUUID) {
        int httpStatus = 200;
        Squad squad = DataHandler.readSquadByUUID(squadUUID);
        if(squad == null){
            httpStatus = 410;
        }
        return Response
                .status(httpStatus)
                .entity(squad)
                .build();
    }

    @POST
    @Path("create")
    @Produces(MediaType.TEXT_PLAIN)
    public Response insertSquad(
            @FormParam("manager") String manager,
            @FormParam("nationality") String nationality
    ){
        Squad squad = new Squad();
        squad.setSquadUUID(UUID.randomUUID().toString());
        squad.setManager(manager);
        squad.setNationality(nationality);

        DataHandler.insertSquad(squad);
        return Response
                .status(200)
                .entity(squad)
                .build();
    }

    @DELETE
    @Path("delete")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteSquad(
            @QueryParam("squadUUID") String squadUUID
    ){
        int httpStatus = 200;
        if (!DataHandler.deleteSquad(squadUUID)){
            httpStatus = 410;
        }
        return Response
                .status(httpStatus)
                .entity("")
                .build();
    }
}
