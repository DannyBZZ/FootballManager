package ch.bzz.footballmanager.service;

import ch.bzz.footballmanager.data.DataHandler;
import ch.bzz.footballmanager.model.Player;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.UUID;

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

    @POST
    @Path("create")
    @Produces(MediaType.TEXT_PLAIN)
    public Response insertPlayer(
            @FormParam("firstname") String firstname,
            @FormParam("lastname") String lastname,
            @FormParam("nationality") String nationality,
            @FormParam("height") double height,
            @FormParam("weight") int weight,
            @FormParam("phonenumber") String phonenumber,
            @FormParam("clubUUID") String clubUUID
    ){
        Player player = new Player();
        player.setPlayerUUID(UUID.randomUUID().toString());
        player.setFirstname(firstname);
        player.setLastname(lastname);
        player.setNationality(nationality);
        player.setHeight(height);
        player.setWeight(weight);
        player.setPhonenumber(phonenumber);
        player.setClubUUID(clubUUID);

        DataHandler.insertPlayer(player);
        return Response
                .status(200)
                .entity(player)
                .build();
    }

    @POST
    @Path("update")
    @Produces(MediaType.TEXT_PLAIN)
    public Response updatePlayer(
            @FormParam("playerUUID") String playerUUID,
            @FormParam("firstname") String firstname,
            @FormParam("lastname") String lastname,
            @FormParam("nationality") String nationality,
            @FormParam("height") double height,
            @FormParam("weight") int weight,
            @FormParam("phonenumber") String phonenumber,
            @FormParam("clubUUID") String clubUUID
    ){
        int httpStatus = 200;
        Player player = DataHandler.getInstance().readPlayerByUUID(playerUUID);
        if(player != null){
            player.setFirstname(firstname);
            player.setLastname(lastname);
            player.setNationality(nationality);
            player.setHeight(height);
            player.setWeight(weight);
            player.setPhonenumber(phonenumber);
            player.setClubUUID(clubUUID);

            DataHandler.updatePlayer();
        }else {
            httpStatus = 410;
        }
        return Response
                .status(httpStatus)
                .entity("")
                .build();
    }


    @DELETE
    @Path("delete")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deletePlayer(
            @QueryParam("playerUUID") String playerUUID
    ){
        int httpStatus = 200;
        if (!DataHandler.deletePlayer(playerUUID)){
            httpStatus = 410;
        }
        return Response
                .status(httpStatus)
                .entity("")
                .build();
    }
}