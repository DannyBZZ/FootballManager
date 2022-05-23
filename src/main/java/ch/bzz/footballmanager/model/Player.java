package ch.bzz.footballmanager.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Player {

    @JsonIgnore
    private Squad squad;
    private Club club;

    private String playerUUID;
    private String firstname;
    private String lastname;
    private String nationality;
    private double height;
    private int weight;
    private String phonenumber;

    /**
     * gets club
     *
     * @return value of club
     */
    public Club getClub() {
        return club;
    }

    /**
     * sets club
     *
     * @param club the value to set
     */
    public void setClub(Club club) {
        this.club = club;
    }

    /**
     * gets squad
     *
     * @return value of squad
     */
    public Squad getSquad() {
        return squad;
    }

    /**
     * sets squad
     *
     * @param squad the value to set
     */
    public void setSquad(Squad squad) {
        this.squad = squad;
    }

    /**
     * gets playerUUID
     *
     * @return value of playerUUID
     */
    public String getPlayerUUID() {
        return playerUUID;
    }

    /**
     * sets playerUUID
     *
     * @param playerUUID the value to set
     */
    public void setPlayerUUID(String playerUUID) {
        this.playerUUID = playerUUID;
    }

    /**
     * gets firstname
     *
     * @return value of firstname
     */
    public String getFirstname() {
        return firstname;
    }

    /**
     * sets firstname
     *
     * @param firstname the value to set
     */
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    /**
     * gets lastname
     *
     * @return value of lastname
     */
    public String getLastname() {
        return lastname;
    }

    /**
     * sets lastname
     *
     * @param lastname the value to set
     */
    public void setLastname(String lastname) {
        this.lastname = lastname;
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

    /**
     * gets height
     *
     * @return value of height
     */
    public double getHeight() {
        return height;
    }

    /**
     * sets height
     *
     * @param height the value to set
     */
    public void setHeight(double height) {
        this.height = height;
    }

    /**
     * gets weight
     *
     * @return value of weight
     */
    public int getWeight() {
        return weight;
    }

    /**
     * sets weight
     *
     * @param weight the value to set
     */
    public void setWeight(int weight) {
        this.weight = weight;
    }

    /**
     * gets phonenumber
     *
     * @return value of phonenumber
     */
    public String getPhonenumber() {
        return phonenumber;
    }

    /**
     * sets phonenumber
     *
     * @param phonenumber the value to set
     */
    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }
}