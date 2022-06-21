package ch.bzz.footballmanager.service;

import ch.bzz.footballmanager.data.DataHandler;
import ch.bzz.footballmanager.model.Player;
import ch.bzz.footballmanager.model.Squad;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
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
     *
     * @return message
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

    /**
     * read a squad by squadUUID
     *
     * @return Response
     */

    @GET
    @Path("read")
    @Produces(MediaType.APPLICATION_JSON)
    public Response readSquad(@NotEmpty
                                @Pattern(regexp = "[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}")
                                @QueryParam("squadUUID") String squadUUID) {
        int httpStatus = 200;
        Squad squad = DataHandler.readSquadByUUID(squadUUID);
        if (squad == null) {
            httpStatus = 410;
        }
        return Response
                .status(httpStatus)
                .entity(squad)
                .build();
    }

    /**
     * inserts a new squad
     *
     * @return Response
     */
    @POST
    @Path("create")
    @Produces(MediaType.TEXT_PLAIN)
    public Response insertSquad(
            @Valid @BeanParam Squad squad
    ) {
        squad.setSquadUUID(UUID.randomUUID().toString());

        DataHandler.insertSquad(squad);
        return Response
                .status(200)
                .entity("")
                .build();
    }

    /**
     * updates a new squad
     *
     * @return Response
     */
    @PUT
    @Path("update")
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateSquad(
            @Valid @BeanParam Squad squad
    ) {
        int httpStatus = 200;
        Squad oldsquad = DataHandler.readSquadByUUID(squad.getSquadUUID());
        if (oldsquad != null) {
            oldsquad.setManager(squad.getManager());
            oldsquad.setNationality(squad.getNationality());

            DataHandler.updateSquad();
        } else {
            httpStatus = 410;
        }
        return Response
                .status(httpStatus)
                .entity("")
                .build();
    }

    /**
     * deletes a squad identified by its squadUUID
     *
     * @param squadUUID the key
     * @return Response
     */
    @DELETE
    @Path("delete")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteSquad(
            @NotEmpty
            @Pattern(regexp = "[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}")
            @QueryParam("squadUUID") String squadUUID
    ) {
        int httpStatus = 200;
        if (!DataHandler.deleteSquad(squadUUID)) {
            httpStatus = 410;
        }
        return Response
                .status(httpStatus)
                .entity("")
                .build();
    }
}