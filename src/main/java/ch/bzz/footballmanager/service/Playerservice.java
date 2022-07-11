package ch.bzz.footballmanager.service;

import ch.bzz.footballmanager.data.DataHandler;
import ch.bzz.footballmanager.model.Player;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
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
     *
     * @return message
     */
    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listPlayers(
            @CookieParam("userRole") String userRole
    ) {
        List<Player> playerList = null;
        int httpStatus;
        if (userRole == null || userRole.equals("guest")) {
            httpStatus = 403;
        } else {
            httpStatus = 200;
            playerList = DataHandler.readAllPlayers();
        }
        return Response
                .status(httpStatus)
                .entity(playerList)
                .build();
    }

    /**
     * read player by playerUUID
     *
     * @return Response
     */

    @GET
    @Path("read")
    @Produces(MediaType.APPLICATION_JSON)
    public Response readPlayers(
            @NotEmpty
            @Pattern(regexp = "[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}") @QueryParam("playerUUID") String playerUUID,
            @CookieParam("userRole") String userRole) {

        int httpStatus;
        Player player = null;

        if (userRole == null || userRole.equals("guest")) {
            httpStatus = 403;
        } else {
            httpStatus = 200;
            player = DataHandler.readPlayerByUUID(playerUUID);
        }

        if (player == null) {
            httpStatus = 410;
        }

        return Response
                .status(httpStatus)
                .entity(player)
                .build();
    }

    /**
     * inserts a new player
     *
     * @return Response
     */
    @POST
    @Path("create")
    @Produces(MediaType.TEXT_PLAIN)
    public Response insertPlayer(
            @Valid @BeanParam Player player,
            @CookieParam("userRole") String userRole
    ) {
        int httpStatus;
        if (userRole == null || userRole.equals("guest")) {
            httpStatus = 403;
        } else {
            httpStatus = 200;
            player.setPlayerUUID(UUID.randomUUID().toString());
            DataHandler.insertPlayer(player);
        }
        return Response
                .status(httpStatus)
                .entity("")
                .build();
    }

    /**
     * updates a new player
     *
     * @return Response
     */
    @PUT
    @Path("update")
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateBook(
            @Valid @BeanParam Player player,
            @CookieParam("userRole") String userRole
    ) {
        int httpStatus;
        Player oldplayer = DataHandler.readPlayerByUUID(player.getPlayerUUID());

        if (userRole == null || userRole.equals("guest")) {
            httpStatus = 403;
        } else {
            httpStatus = 200;
            if (oldplayer != null) {
                oldplayer.setFirstname(player.getFirstname());
                oldplayer.setLastname(player.getLastname());
                oldplayer.setNationality(player.getNationality());
                oldplayer.setWeight(player.getWeight());
                oldplayer.setHeight(player.getHeight());
                oldplayer.setPhonenumber(player.getPhonenumber());
                DataHandler.updatePlayer();
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
     * deletes a player identified by its playerUUID
     *
     * @param playerUUID the key
     * @return Response
     */
    @DELETE
    @Path("delete")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deletePlayer(
            @NotEmpty
            @Pattern(regexp = "[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}")
            @QueryParam("playerUUID") String playerUUID,
            @CookieParam("userRole") String userRole
    ) {
        int httpStatus;

        if (userRole == null || userRole.equals("guest")) {
            httpStatus = 403;
        } else {
            httpStatus = 200;
            if (!DataHandler.deletePlayer(playerUUID)) {
                httpStatus = 410;
            }
        }

        return Response
                .status(httpStatus)
                .entity("")
                .build();
    }
}