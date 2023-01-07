package de.hebk.gamemodes.mutliplayer;

import de.hebk.game.Question;
import de.hebk.SQLManager;
import de.hebk.model.list.List;
import de.hebk.multiplayer.ClientConnection;
import de.hebk.multiplayer.Packet;
import de.hebk.multiplayer.PacketType;
import de.hebk.multiplayer.Server;

import java.util.HashMap;
import java.util.Map;

public class MultiplayerNormal extends MultiplayerGamemode {
    private List<Question> questions = new List<>();

    /**
     * Contructor for a normal multiplayer game
     * @param connections   Every connection to the clients
     * @param sqlManager    SQLManager
     * @param server        The server the game is running on
     */
    public MultiplayerNormal(List<ClientConnection> connections, SQLManager sqlManager, Server server) {
        super(connections, sqlManager, server);
    }

    /**
     * Starts a normal multiplayer game
     */
    public void startGame() {
        for (int i = 1; i < 16; i++) {
            questions.concat(sqlManager.getRandomQuestionsFromLevel(i, 4));
        }

        for (int i = 1; i < 16; i++) {
            // asks a question to every player
            questions.toFirst();
            Question[] q = new Question[4];
            for (int j = 0; j < 4; j++) {
                q[j] = questions.getObject();
                questions.remove();
            }

            Question question = selectPlayerQuestion(q);
            question.shuffleAnswers();
            askQuestion(question);

            // gets every answer from the players and decides if they failed
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

    @Override
    public String convertLevelToMoney(int level) {
        String money = "";

        switch (level) {
            case 1 -> money = "50";
            case 2 -> money = "100";
            case 3 -> money = "200";
            case 4 -> money = "300";
            case 5 -> money = "500";
            case 6 -> money = "1.000";
            case 7 -> money = "2.000";
            case 8 -> money = "4.000";
            case 9 -> money = "8.000";
            case 10 -> money = "16.000";
            case 11 -> money = "32.000";
            case 12 -> money = "64.000";
            case 13 -> money = "125.000";
            case 14 -> money = "500.000";
            case 15 -> money = "1.000.000";
            default -> money = "0";
        }

        return money;
    }
}