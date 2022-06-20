package ch.bzz.footballmanager.model;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.ws.rs.FormParam;

public class Squad {

    @FormParam("squadUUID")
    @Pattern(regexp = "[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}")
    private String squadUUID;

    @FormParam("manager")
    @Size(min = 2, max = 45)
    private String manager;

    @FormParam("nationality")
    @Size(min = 2, max = 45)
    private String nationality;

    /**
     * gets squadUUID
     *
     * @return value of squadUUID
     */
    public String getSquadUUID() {
        return squadUUID;
    }

    /**
     * sets squadUUID
     *
     * @param squadUUID the value to set
     */
    public void setSquadUUID(String squadUUID) {
        this.squadUUID = squadUUID;
    }

    /**
     * gets manager
     *
     * @return value of manager
     */
    public String getManager() {
        return manager;
    }

    /**
     * sets manager
     *
     * @param manager the value to set
     */
    public void setManager(String manager) {
        this.manager = manager;
    }

    /**
     * gets nationality
     *
     * @return value of nationality
     */
    public String getNationality() {
        return nationality;
    }

    /**
     * sets nationality
     *
     * @param nationality the value to set
     */
    public void setNationality(String nationality) {
        this.nationality = nationality;
    }
}
