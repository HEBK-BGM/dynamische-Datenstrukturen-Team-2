package de.hebk.gamemodes.mutliplayer;

import com.google.gson.Gson;
import de.hebk.Question;
import de.hebk.SQLManager;
import de.hebk.model.list.List;
import de.hebk.multiplayer.ClientConnection;
import de.hebk.multiplayer.Packet;
import de.hebk.multiplayer.PacketType;

import java.util.HashMap;
import java.util.Map;

public class MultiplayerNormal extends MultiplayerGamemode {
    /**
     * Contructor for a normal multiplayer game
     * @param connections   Every connection to the clients
     * @param sqlManager    SQLManager
     */
    public MultiplayerNormal(List<ClientConnection> connections, SQLManager sqlManager) {
        super(connections, sqlManager);
    }

    /**
     * Starts a normal multiplayer game
     */
    public void startGame() {
        for (int i = 1; i < 16; i++) {
            Question question = selectPlayerQuestion(i);

            connections.toFirst();
            Packet packet = new Packet(PacketType.CLEAR, "");
            for (int j = 0; j < connections.size(); j++) {
                connections.getObject().send(packet);
                connections.next();
            }

            askQuestion(question);
        }
    }
}