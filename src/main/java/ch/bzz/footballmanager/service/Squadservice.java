package ch.bzz.footballmanager.service;

import ch.bzz.footballmanager.data.DataHandler;
import ch.bzz.footballmanager.model.Player;
import ch.bzz.footballmanager.model.Squad;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("squad")
public class Squadservice {
    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listSquads() {
        List<Squad> squadList = DataHandler.getInstance().readAllSquads();
        return Response
                .status(200)
                .entity(squadList)
                .build();
    }

    @GET
    @Path("read")
    @Produces(MediaType.APPLICATION_JSON)
    public Response readPlayers(@QueryParam("squadUUID") String squadUUID) {
        Squad squad = DataHandler.getInstance().readSquadByUUID(squadUUID);
        return Response
                .status(200)
                .entity(squad)
                .build();
    }
}
