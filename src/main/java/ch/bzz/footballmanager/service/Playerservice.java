package ch.bzz.footballmanager.service;

import ch.bzz.footballmanager.data.DataHandler;
import ch.bzz.footballmanager.model.Player;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * playertest service
 */
@Path("player")
public class Playerservice {

    /**
     * confirms the application runs
     * @return  message
     */
    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listPlayers() {
        List<Player> playerList = DataHandler.getInstance().readAllPlayers();
        return Response
                .status(200)
                .entity(playerList)
                .build();
    }

    @GET
    @Path("read")
    @Produces(MediaType.APPLICATION_JSON)
    public Response readPlayers(@QueryParam("playerUUID") String playerUUID) {
        int httpStatus = 200;
        Player player = DataHandler.getInstance().readPlayerByUUID(playerUUID);
        if(player == null){
            httpStatus = 410;
        }
        return Response
                .status(httpStatus)
                .entity(player)
                .build();
    }
}