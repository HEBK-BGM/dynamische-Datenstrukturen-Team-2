package de.hebk.gamemodes.mutliplayer;

import de.hebk.game.Question;
import de.hebk.game.SQLManager;
import de.hebk.model.list.List;
import de.hebk.model.queue.Queue;
import de.hebk.multiplayer.*;

import java.util.HashMap;
import java.util.Map;

public class MultiplayerTrueOrNot extends MultiplayerGamemode {
    private Queue<Question> questions = new Queue<>();

    /**
     * Contructor for a true or not multiplayer game
     * @param connections   Every connection to the clients
     * @param sqlManager    SQLManager
     * @param server        The server the game is running on
     */
    public MultiplayerTrueOrNot(List<ClientConnection> connections, SQLManager sqlManager, Server server) {
        super(connections, sqlManager, server);
    }

    /**
     * Starts a true or not multiplayer game
     */
    public void startGame() {
        for (int i = 1; i < 30; i++) {
            int num = i/2;
            if (num == 0) {
                num = 1;
            }

            List<Question> q = sqlManager.getRandomQuestionsFromLevel(num, 4);

            q.toFirst();
            for (int j = 0; j < 4; j++) {
                questions.enqueue(q.getObject());
                q.next();
            }
        }

        for (int i = 0; i < 30; i++) {
            Question[] q = new Question[4];
            for (int j = 0; j < 4; j++) {
                q[j] = questions.dequeue();
            }

            Question question = selectPlayerQuestion(q);
            question.shuffleAnswers();
            askQuestion(question);

            // gets every answer from the players and decides if they failed
            HashMap<ClientConnection, String> answers = getAnswers();
            Packet failPacket = new Packet(PacketType.WRONG_ANSWER, "");
            for (Map.Entry<ClientConnection, String> entry : answers.entrySet() ) {
                if (entry.getValue().equals("false")) {
                    System.out.println(entry.getKey().getUsername() + " has failed");
                    entry.getKey().setFailed(true);
                    entry.getKey().send(failPacket);
                }
            }

            checkGameStatus(i, 30);
        }
    }
}