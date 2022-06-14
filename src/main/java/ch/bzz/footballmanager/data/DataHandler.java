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
    private static DataHandler instance = null;
    private List<Player> playerList;
    private List<Squad> squadList;
    private List<Club> clubList;

    /**
     * private constructor defeats instantiation
     */
    private DataHandler(){
        setPlayerList(new ArrayList<>());
        readPlayerJSON();
        setSquadList(new ArrayList<>());
        readSquadJSON();
        setClubList(new ArrayList<>());
        readClubJSON();
    }

    /**
     * gets the only instance of this class
     * @return
     */
    public static DataHandler getInstance() {
        if (instance == null)
            instance = new DataHandler();
        return instance;
    }

    /**
     * reads a player by its uuid
     * @param playerUUID
     * @return the Player (null=not found)
     */
    public Player readPlayerByUUID(String playerUUID){
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
    public Squad readSquadByUUID(String squadUUID){
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
    public Club readClubByUUID(String clubUUID){
        Club club = null;
        for (Club entry: getClubList()){
            if (entry.getClubUUID().equals(clubUUID)){
                club = entry;
            }
        }
        return club;
    }

    /**
     * reads the players from the JSON-file
     */
    private void readPlayerJSON() {
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
    private void readSquadJSON() {
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
    private void readClubJSON() {
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

    public static void insertPlayer(Player player) {
        getInstance().getPlayerList().add(player);
        writePlayerJSON();
    }

    public static void insertSquad(Squad squad) {
        getInstance().getSquadList().add(squad);
        writeSquadJSON();
    }

    /**
     * updates the playetList
     */
    public static void updatePlayer() {
        writePlayerJSON();
    }

    public static void updateSquad() {
        writeSquadJSON();
    }

    public static boolean deletePlayer(String playerUUID) {
        Player player = instance.readPlayerByUUID(playerUUID);
        if (player != null) {
            getInstance().getPlayerList().remove(player);
            writePlayerJSON();
            return true;
        } else {
            return false;
        }
    }

    public static boolean deleteSquad(String squadUUID) {
        Squad squad = instance.readSquadByUUID(squadUUID);
        if (squad != null) {
            getInstance().getSquadList().remove(squad);
            writeSquadJSON();
            return true;
        } else {
            return false;
        }
    }

    private static void writePlayerJSON() {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectWriter objectWriter = objectMapper.writer(new DefaultPrettyPrinter());
        FileOutputStream fileOutputStream = null;
        Writer fileWriter;

        String playerPath = Config.getProperty("playerJSON");
        try {
            fileOutputStream = new FileOutputStream(playerPath);
            fileWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream, StandardCharsets.UTF_8));
            objectWriter.writeValue(fileWriter, instance.getPlayerList());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private static void writeSquadJSON() {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectWriter objectWriter = objectMapper.writer(new DefaultPrettyPrinter());
        FileOutputStream fileOutputStream = null;
        Writer fileWriter;

        String squadPath = Config.getProperty("squadJSON");
        try {
            fileOutputStream = new FileOutputStream(squadPath);
            fileWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream, StandardCharsets.UTF_8));
            objectWriter.writeValue(fileWriter, instance.getSquadList());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * reads all players
     * @return list of players
     */
    public List<Player> readAllPlayers(){
        return getPlayerList();
    }

    /**
     * reads all squads
     * @return list of squads
     */
    public List<Squad> readAllSquads(){
        return getSquadList();
    }

    /**
     * reads all clubs
     * @return list of clubs
     */
    public List<Club> readAllClubs(){
        return getClubList();
    }

    /**
     * gets playerList
     *
     * @return value of playerList
     */
    private List<Player> getPlayerList(){
        return playerList;
    }

    /**
     * gets squadList
     *
     * @return value of squadList
     */
    private List<Squad> getSquadList(){
        return squadList;
    }

    /**
     * gets clubList
     *
     * @return value of clubList
     */
    public List<Club> getClubList() {
        return clubList;
    }

    /**
     * sets clubList
     *
     * @param clubList the value to set
     */
    public void setClubList(List<Club> clubList) {
        this.clubList = clubList;
    }

    /**
     * sets playerList
     *
     * @param playerList the value to set
     */
    public void setPlayerList(List<Player> playerList) {
        this.playerList = playerList;
    }

    /**
     * sets squadList
     *
     * @param squadList the value to set
     */
    public void setSquadList(List<Squad> squadList) {
        this.squadList = squadList;
    }
}
