package ch.bzz.footballmanager.model;

public class Squad {
    private String squadUUID;
    private String manager;
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
