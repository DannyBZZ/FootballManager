package ch.bzz.footballmanager.model;

import javax.validation.constraints.*;
import javax.ws.rs.FormParam;

public class Player {

    @FormParam("playerUUID")
    @Pattern(regexp = "[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}")
    private String playerUUID;

    @FormParam("firstname")
    @Size(min = 2, max = 45)
    private String firstname;

    @FormParam("lastname")
    @Size(min = 2, max = 45)
    private String lastname;

    @FormParam("nationality")
    @Size(min = 2, max = 45)
    private String nationality;

    @FormParam("height")
    @Max(value = 3)
    private double height;

    @FormParam("weight")
    @Max(value = 250)
    private int weight;

    @FormParam("phonenumber")
    @Size(min = 2, max = 13)
    private String phonenumber;

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