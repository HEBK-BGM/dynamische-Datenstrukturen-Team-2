package de.hebk.gamemodes.mutliplayer;

import de.hebk.game.Question;
import de.hebk.SQLManager;
import de.hebk.model.list.List;
import de.hebk.multiplayer.ClientConnection;
import de.hebk.multiplayer.Packet;
import de.hebk.multiplayer.PacketType;

public class MultiplayerTrueOrNot extends MultiplayerGamemode {
    /**
     * Contructor for a true or not multiplayer game
     * @param connections   Every connection to the clients
     * @param sqlManager    SQLManager
     */
    public MultiplayerTrueOrNot(List<ClientConnection> connections, SQLManager sqlManager) {
        super(connections, sqlManager);
    }

    /**
     * Starts a true or not multiplayer game
     */
    public void startGame() {
        for (int i = 0; i < 30; i++) {
            int num = i/2;
            if (num == 0) {
                num = 1;
            }

            Question question = selectPlayerQuestion(num);

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