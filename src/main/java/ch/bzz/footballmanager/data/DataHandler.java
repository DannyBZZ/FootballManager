package ch.bzz.footballmanager.data;

import ch.bzz.footballmanager.model.Club;
import ch.bzz.footballmanager.model.Player;
import ch.bzz.footballmanager.model.Squad;
import ch.bzz.footballmanager.service.Config;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * reads and writes the data in the JSON-files
 */
public class DataHandler {
    private static List<Player> playerList;
    private static List<Squad> squadList;
    private static List<Club> clubList;

    /**
     * private constructor defeats instantiation
     */
    private DataHandler(){

    }

    /**
     * initialize the lists with the data
     */
    public static void initLists() {
        DataHandler.setPlayerList(null);
        DataHandler.setSquadList(null);
        DataHandler.setClubList(null);
    }

    /**
     * reads a player by its uuid
     * @param playerUUID
     * @return the Player (null=not found)
     */
    public static Player readPlayerByUUID(String playerUUID){
        Player player = null;
        for (Player entry: getPlayerList()){
            if (entry.getPlayerUUID().equals(playerUUID)){
                player = entry;
            }
        }
        return player;
    }

    /**
     * reads a squad by its uuid
     * @param squadUUID
     * @return the Squad (null=not found)
     */
    public static Squad readSquadByUUID(String squadUUID){
        Squad squad = null;
        for (Squad entry: getSquadList()){
            if (entry.getSquadUUID().equals(squadUUID)){
                squad = entry;
            }
        }
        return squad;
    }

    /**
     * reads a club by its uuid
     * @param clubUUID
     * @return the Club (null=not found)
     */
    public static Club readClubByUUID(String clubUUID){
        Club club = null;
        for (Club entry: getClubList()){
            if (entry.getClubUUID().equals(clubUUID)){
                club = entry;
            }
        }
        return club;
    }

    /**
     * updates the playerList
     */
    public static void updatePlayer() {
        writePlayerJSON();
    }

    /**
     * updates the squadList
     */
    public static void updateSquad() {
        writeSquadJSON();
    }

    /**
     * updates the clubList
     */
    public static void updateClub() {
        writeClubJSON();
    }

    /**
     * inserts a new player into the playerlist
     *
     * @param player the book to be saved
     */
    public static void insertPlayer(Player player) {
        getPlayerList().add(player);
        writePlayerJSON();
    }

    /**
     * inserts a new squad into the squadlist
     *
     * @param squad the squad to be saved
     */
    public static void insertSquad(Squad squad) {
        getSquadList().add(squad);
        writeSquadJSON();
    }

    /**
     * inserts a new club into the clublist
     *
     * @param club the squad to be saved
     */
    public static void insertClub(Club club) {
        getClubList().add(club);
        writeClubJSON();
    }

    /**
     * deletes a player identified by the playerUUID
     * @param playerUUID  the key
     * @return  success=true/false
     */
    public static boolean deletePlayer(String playerUUID) {
        Player player = readPlayerByUUID(playerUUID);
        if (player != null) {
            getPlayerList().remove(player);
            writePlayerJSON();
            return true;
        } else {
            return false;
        }
    }

    /**
     * deletes a squad identified by the squadUUID
     * @param squadUUID  the key
     * @return  success=true/false
     */
    public static boolean deleteSquad(String squadUUID) {
        Squad squad = readSquadByUUID(squadUUID);
        if (squad != null) {
            getSquadList().remove(squad);
            writeSquadJSON();
            return true;
        } else {
            return false;
        }
    }

    /**
     * deletes a club identified by the clubUUID
     * @param clubUUID  the key
     * @return  success=true/false
     */
    public static boolean deleteClub(String clubUUID) {
        Club club = readClubByUUID(clubUUID);
        if (club != null) {
            getClubList().remove(club);
            writeClubJSON();
            return true;
        } else {
            return false;
        }
    }

    /**
     * reads the players from the JSON-file
     */
    private static void readPlayerJSON() {
        try {
            String path = Config.getProperty("playerJSON");
            byte[] jsonData = Files.readAllBytes(
                    Paths.get(path)
            );
            ObjectMapper objectMapper = new ObjectMapper();
            Player[] players = objectMapper.readValue(jsonData, Player[].class);
            for (Player player : players) {
                getPlayerList().add(player);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * reads the squads from the JSON-file
     */
    private static void readSquadJSON() {
        try {
            byte[] jsonData = Files.readAllBytes(
                    Paths.get(
                            Config.getProperty("squadJSON")
                    )
            );
            ObjectMapper objectMapper = new ObjectMapper();
            Squad[] squads = objectMapper.readValue(jsonData, Squad[].class);
            for (Squad squad : squads) {
                getSquadList().add(squad);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * reads the clubs from the JSON-file
     */
    private static void readClubJSON() {
        try {
            byte[] jsonData = Files.readAllBytes(
                    Paths.get(
                            Config.getProperty("clubJSON")
                    )
            );
            ObjectMapper objectMapper = new ObjectMapper();
            Club[] clubs = objectMapper.readValue(jsonData, Club[].class);
            for (Club club : clubs) {
                getClubList().add(club);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * writes the bookList to the JSON-file
     */
    private static void writePlayerJSON() {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectWriter objectWriter = objectMapper.writer(new DefaultPrettyPrinter());
        FileOutputStream fileOutputStream = null;
        Writer fileWriter;

        String bookPath = Config.getProperty("playerJSON");
        try {
            fileOutputStream = new FileOutputStream(bookPath);
            fileWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream, StandardCharsets.UTF_8));
            objectWriter.writeValue(fileWriter, getPlayerList());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * writes the squadList to the JSON-file
     */
    private static void writeSquadJSON() {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectWriter objectWriter = objectMapper.writer(new DefaultPrettyPrinter());
        FileOutputStream fileOutputStream = null;
        Writer fileWriter;

        String bookPath = Config.getProperty("squadJSON");
        try {
            fileOutputStream = new FileOutputStream(bookPath);
            fileWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream, StandardCharsets.UTF_8));
            objectWriter.writeValue(fileWriter, getSquadList());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * writes the clubList to the JSON-file
     */
    private static void writeClubJSON() {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectWriter objectWriter = objectMapper.writer(new DefaultPrettyPrinter());
        FileOutputStream fileOutputStream = null;
        Writer fileWriter;

        String bookPath = Config.getProperty("clubJSON");
        try {
            fileOutputStream = new FileOutputStream(bookPath);
            fileWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream, StandardCharsets.UTF_8));
            objectWriter.writeValue(fileWriter, getClubList());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * reads all players
     * @return list of players
     */
    public static List<Player> readAllPlayers(){
        return getPlayerList();
    }

    /**
     * reads all squads
     * @return list of squads
     */
    public static List<Squad> readAllSquads(){
        return getSquadList();
    }

    /**
     * reads all clubs
     * @return list of clubs
     */
    public static List<Club> readAllClubs(){
        return getClubList();
    }

    /**
     * gets playerList
     *
     * @return value of playerList
     */
    private static List<Player> getPlayerList(){
        if (playerList == null) {
            setPlayerList(new ArrayList<>());
            readPlayerJSON();
        }

        return playerList;
    }

    /**
     * gets squadList
     *
     * @return value of squadList
     */
    private static List<Squad> getSquadList(){
        if (squadList == null) {
            setSquadList(new ArrayList<>());
            readSquadJSON();
        }
        return squadList;
    }

    /**
     * gets clubList
     *
     * @return value of clubList
     */
    public static List<Club> getClubList() {
        if (clubList == null) {
            setClubList(new ArrayList<>());
            readClubJSON();
        }
        return clubList;
    }

    /**
     * sets clubList
     *
     * @param clubList the value to set
     */
    public static void setClubList(List<Club> clubList) {
        DataHandler.clubList = clubList;
    }

    /**
     * sets playerList
     *
     * @param playerList the value to set
     */
    public static void setPlayerList(List<Player> playerList) {
        DataHandler.playerList = playerList;
    }

    /**
     * sets squadList
     *
     * @param squadList the value to set
     */
    public static void setSquadList(List<Squad> squadList) {
        DataHandler.squadList = squadList;
    }
}
