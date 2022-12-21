package de.hebk.gamemodes.mutliplayer;

import de.hebk.game.Question;
import de.hebk.SQLManager;
import de.hebk.model.list.List;
import de.hebk.multiplayer.ClientConnection;
import de.hebk.multiplayer.Packet;
import de.hebk.multiplayer.PacketType;

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
        for (int i = 1; i < 16; i++) {
            Question question = selectPlayerQuestion(15);

            connections.toFirst();
            Packet packet = new Packet(PacketType.CLEAR, "");
            for (int j = 0; j < connections.size(); j++) {
                connections.getObject().send(packet);
                connections.next();
            }

            askQuestion(question);
            getAnswers();
        }
    }
}