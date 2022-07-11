package ch.bzz.footballmanager.model;


import javax.ws.rs.FormParam;

public class Club {
    @FormParam("clubUUID")
    private String clubUUID;
    @FormParam("name")
    private String name;
    @FormParam("league")
    private String league;
    @FormParam("stadium")
    private String stadium;

    /**
     * gets clubUUID
     *
     * @return value of clubUUID
     */
    public String getClubUUID() {
        return clubUUID;
    }

    /**
     * sets clubUUID
     *
     * @param clubUUID the value to set
     */
    public void setClubUUID(String clubUUID) {
        this.clubUUID = clubUUID;
    }

    /**
     * gets name
     *
     * @return value of name
     */
    public String getName() {
        return name;
    }

    /**
     * sets name
     *
     * @param name the value to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * gets league
     *
     * @return value of league
     */
    public String getLeague() {
        return league;
    }

    /**
     * sets league
     *
     * @param league the value to set
     */
    public void setLeague(String league) {
        this.league = league;
    }

    /**
     * gets stadium
     *
     * @return value of stadium
     */
    public String getStadium() {
        return stadium;
    }

    /**
     * sets stadium
     *
     * @param stadium the value to set
     */
    public void setStadium(String stadium) {
        this.stadium = stadium;
    }
}
