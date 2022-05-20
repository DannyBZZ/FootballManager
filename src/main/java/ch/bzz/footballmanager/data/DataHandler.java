package ch.bzz.footballmanager.data;

import ch.bzz.footballmanager.model.Player;
import ch.bzz.footballmanager.model.Squad;
import ch.bzz.footballmanager.service.Config;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class DataHandler {
    private static DataHandler instance = null;
    private List<Player> playerList;
    private List<Squad> squadList;

    private DataHandler(){
        setPlayerList(new ArrayList<>());
        readPlayerJSON();
        setSquadList(new ArrayList<>());
        readSquadJSON();
    }

    public static DataHandler getInstance() {
        if (instance == null)
            instance = new DataHandler();
        return instance;
    }

    public List<Player> readAllPlayers(){
        return getPlayerList();
    }

    public Player readPlayerByUUID(String playerUUID){
        Player player = null;
        for (Player entry: getPlayerList()){
            if (entry.getPlayerUUID().equals(playerUUID)){
                player = entry;
            }
        }
        return player;
    }

    private List<Player> getPlayerList(){
        return playerList;
    }

    public List<Squad> readAllSquads(){
        return getSquadList();
    }

    public Squad readSquadByUUID(String squadUUID){
        Squad squad = null;
        for (Squad entry: getSquadList()){
            if (entry.getSquadUUID().equals(squadUUID)){
                squad = entry;
            }
        }
        return squad;
    }

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
     * reads the publishers from the JSON-file
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

    private List<Squad> getSquadList(){
        return squadList;
    }

    public void setPlayerList(List<Player> playerList) {
        this.playerList = playerList;
    }

    public void setSquadList(List<Squad> squadList) {
        this.squadList = squadList;
    }
}
