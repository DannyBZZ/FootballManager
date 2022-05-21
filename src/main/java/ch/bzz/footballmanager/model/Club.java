package ch.bzz.footballmanager.model;


public class Club {
    private String clubUUID;
    private String name;
    private String league;
    private String stadium;

    public String getClubUUID() {
        return clubUUID;
    }

    public void setClubUUID(String clubUUID) {
        this.clubUUID = clubUUID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLeague() {
        return league;
    }

    public void setLeague(String league) {
        this.league = league;
    }

    public String getStadium() {
        return stadium;
    }

    public void setStadium(String stadium) {
        this.stadium = stadium;
    }
}
