package ch.bzz.footballmanager.service;

import ch.bzz.footballmanager.data.DataHandler;
import ch.bzz.footballmanager.model.Player;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.math.BigDecimal;
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
        List<Player> playerList = DataHandler.readAllPlayers();
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
        Player player = DataHandler.readPlayerByUUID(playerUUID);
        if(player == null){
            httpStatus = 410;
        }
        return Response
                .status(httpStatus)
                .entity(player)
                .build();
    }

    /**
     * inserts a new player
     * @param firstname the firstname of the player
     * @param lastname the lastname of the player
     * @param nationality the nationality of the player
     * @param height the height of the player
     * @param weight the weight of the player
     * @param phonenumber the phonenumber of the player
     * @return Response
     */
    @POST
    @Path("create")
    @Produces(MediaType.TEXT_PLAIN)
    public Response insertPlayer(
            @FormParam("firstname") String firstname,
            @FormParam("lastname") String lastname,
            @FormParam("nationality") String nationality,
            @FormParam("height") double height,
            @FormParam("weight") int weight,
            @FormParam("phonenumber") String phonenumber
    ) {
        Player player = new Player();
        player.setPlayerUUID(UUID.randomUUID().toString());
        setAttributes(
                player,
                firstname,
                lastname,
                nationality,
                height,
                weight,
                phonenumber
        );

        DataHandler.insertPlayer(player);
        return Response
                .status(200)
                .entity("")
                .build();
    }

    /**
     * updates a new player
     * @param playerUUID the key
     * @param firstname the firstname of the player
     * @param lastname the lastname of the player
     * @param nationality the nationality of the player
     * @param height the height of the player
     * @param weight the weight of the player
     * @param phonenumber the phonenumber of the player
     * @return Response
     */
    @PUT
    @Path("update")
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateBook(
            @FormParam("playerUUID") String playerUUID,
            @FormParam("firstname") String firstname,
            @FormParam("lastname") String lastname,
            @FormParam("nationality") String nationality,
            @FormParam("height") double height,
            @FormParam("weight") int weight,
            @FormParam("phonenumber") String phonenumber
    ) {
        int httpStatus = 200;
        Player player = DataHandler.readPlayerByUUID(playerUUID);
        if (player != null) {
            setAttributes(
                    player,
                    firstname,
                    lastname,
                    nationality,
                    height,
                    weight,
                    phonenumber
            );

            DataHandler.updatePlayer();
        } else {
            httpStatus = 410;
        }
        return Response
                .status(httpStatus)
                .entity("")
                .build();
    }

    /**
     * deletes a player identified by its playerUUID
     * @param playerUUID  the key
     * @return  Response
     */
    @DELETE
    @Path("delete")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deletePlayer(
            @QueryParam("playerUUID") String playerUUID
    ) {
        int httpStatus = 200;
        if (!DataHandler.deletePlayer(playerUUID)) {
            httpStatus = 410;
        }
        return Response
                .status(httpStatus)
                .entity("")
                .build();
    }

    /**
     * sets the attributes for the player-object
     * @param player the player-object
     * @param firstname the firstname of the player
     * @param lastname the lastname of the player
     * @param nationality the nationality of the player
     * @param height the height of the player
     * @param weight the weight of the player
     * @param phonenumber the phonenumber of the player
     */
    private void setAttributes(
            Player player,
            String firstname,
            String lastname,
            String nationality,
            double height,
            int weight,
            String phonenumber
    ) {
        player.setFirstname(firstname);
        player.setLastname(lastname);
        player.setNationality(nationality);
        player.setHeight(height);
        player.setWeight(weight);
        player.setPhonenumber(phonenumber);
    }
}