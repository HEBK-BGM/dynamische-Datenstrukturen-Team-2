package de.hebk.gamemodes.mutliplayer;

import de.hebk.game.Question;
import de.hebk.SQLManager;
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

    @Override
    public String convertLevelToMoney(int level) {
        String money = "";

        switch (level) {
            case 1 -> money = "25";
            case 2 -> money = "50";
            case 3 -> money = "75";
            case 4 -> money = "100";
            case 5 -> money = "150";
            case 6 -> money = "200";
            case 7 -> money = "250";
            case 8 -> money = "300";
            case 9 -> money = "400";
            case 10 -> money = "500";
            case 11 -> money = "750";
            case 12 -> money = "1.000";
            case 13 -> money = "1.500";
            case 14 -> money = "2.000";
            case 15 -> money = "3.000";
            case 16 -> money = "4.000";
            case 17 -> money = "6.000";
            case 18 -> money = "8.000";
            case 19 -> money = "12.000";
            case 20 -> money = "16.000";
            case 21 -> money = "24.000";
            case 22 -> money = "32.000";
            case 23 -> money = "48.000";
            case 24 -> money = "64.000";
            case 25 -> money = "94.500";
            case 26 -> money = "125.000";
            case 27 -> money = "312.500";
            case 28 -> money = "500.000";
            case 29 -> money = "750.000";
            case 30 -> money = "1.000.000";
            default -> money = "0";
        }

        return money;
    }
}