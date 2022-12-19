package de.hebk.gamemodes.mutliplayer;

import com.google.gson.Gson;
import de.hebk.SQLManager;
import de.hebk.model.list.List;
import de.hebk.multiplayer.ClientConnection;

public class MultiplayerTrueOrNot {
    private List<ClientConnection> connections;
    private Gson gson;
    private SQLManager sqlManager;

    /**
     * Contructor for a true or not multiplayer game
     * @param connections   Every connection to the clients
     * @param sqlManager    SQLManager
     */
    public MultiplayerTrueOrNot(List<ClientConnection> connections, SQLManager sqlManager) {
        this.connections = connections;
        this.gson = new Gson();
        this.sqlManager = sqlManager;
    }

    public void startGame() {

    }
}