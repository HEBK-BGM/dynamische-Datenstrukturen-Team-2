package de.hebk.gamemodes.mutliplayer;

import de.hebk.game.Question;
import de.hebk.game.SQLManager;
import de.hebk.model.list.List;
import de.hebk.model.stack.Stack;
import de.hebk.multiplayer.ClientConnection;
import de.hebk.multiplayer.Packet;
import de.hebk.multiplayer.PacketType;
import de.hebk.multiplayer.Server;

import java.util.HashMap;
import java.util.Map;

public class MultiplayerHardcore extends MultiplayerGamemode {
    private Stack<Question> questions = new Stack<>();

    /**
     * Contructor for a hardcore multiplayer game
     * @param connections   Every connection to the clients
     * @param sqlManager    SQLManager
     * @param server        The server the game is running on
     */
    public MultiplayerHardcore(List<ClientConnection> connections, SQLManager sqlManager, Server server) {
        super(connections, sqlManager, server);
    }

    public void startGame() {
        for (int i = 1; i < 16; i++) {
            for (int j = 0; j < 4; j++) {
                List<Question> q = sqlManager.getRandomQuestionsFromLevel(15, 1);
                q.toFirst();
                questions.push(q.getObject());
            }
        }

        for (int i = 1; i < 16; i++) {
            Question[] q = new Question[4];
            for (int j = 0; j < 4; j++) {
                q[j] = questions.pop();
            }

            Question question = selectPlayerQuestion(q);
            question.shuffleAnswers();

            askQuestion(question);
            HashMap<ClientConnection, String> answers = getAnswers();
            Packet failPacket = new Packet(PacketType.WRONG_ANSWER, "");
            for (Map.Entry<ClientConnection, String> entry : answers.entrySet()) {
                if (!entry.getValue().equals(question.getCorrect() + "")) {
                    entry.getKey().setFailed(true);
                    entry.getKey().send(failPacket);

                    System.out.println(entry.getKey().getUsername() + " has failed");
                }
            }

            checkGameStatus(i, 15);
        }
    }
}