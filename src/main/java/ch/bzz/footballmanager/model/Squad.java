package ch.bzz.footballmanager.model;

public class Squad {
    private String squadUUID;
    private String manager;
    private String nationality;

    public String getSquadUUID() {
        return squadUUID;
    }

    public void setSquadUUID(String squadUUID) {
        this.squadUUID = squadUUID;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }
}
