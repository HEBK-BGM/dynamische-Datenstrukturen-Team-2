package de.hebk.gamemodes.mutliplayer;

import com.google.gson.Gson;
import de.hebk.SQLManager;
import de.hebk.model.list.List;
import de.hebk.multiplayer.ClientConnection;

public class MultiplayerHardcore extends MultiplayerGamemode {
    /**
     * Contructor for a hardcore multiplayer game
     * @param connections   Every connection to the clients
     * @param sqlManager    SQLManager
     */
    public MultiplayerHardcore(List<ClientConnection> connections, SQLManager sqlManager) {
        super(connections, sqlManager);
    }

    public void startGame() {

    }
}