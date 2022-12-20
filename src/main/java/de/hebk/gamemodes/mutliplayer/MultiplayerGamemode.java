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

abstract class MultiplayerGamemode {
    protected List<ClientConnection> connections;
    protected Gson gson;
    protected SQLManager sqlManager;

    /**
     * Contructor for a mutliplayer gamemode
     * @param connections   Every connection to the clients
     * @param sqlManager    SQLManager
     */
    public MultiplayerGamemode(List<ClientConnection> connections, SQLManager sqlManager) {
        this.connections = connections;
        this.gson = new Gson();
        this.sqlManager = sqlManager;
    }

    public abstract void startGame();

    /**
     * Picks a random player and the player picks a question
     * @param level Question level
     * @return      Question the player has selected
     */
    protected Question selectPlayerQuestion(int level) {
        Question[] questions = new Question[4];
        List<Question> questionList = sqlManager.getRandomQuestionsFromLevel(level, 4);
        questionList.toFirst();

        for (int i = 0; i < questionList.size(); i++) {
            questions[i] = questionList.getObject();
            questionList.next();;
        }

        String[] questionString = new String[4];
        for (int i = 0; i < questions.length; i++) {
            questionString[i] = questions[i].getBody();
        }

        int random = (int) (Math.random() * connections.size());

        ClientConnection choosen = null;
        connections.toFirst();
        for (int i = 0; i < connections.size(); i++) {
            if (i != random) {
                connections.next();
            }
            else {
                choosen = connections.getObject();
                break;
            }
        }

        Packet packet1 = new Packet(PacketType.QUESTION_IS_SELECTED, choosen.getUsername());
        Packet packet2 = new Packet(PacketType.SELECT_QUESTION, gson.toJson(questionString));

        connections.toFirst();
        for (int i = 0; i < connections.size(); i++) {
            if (i != random) {
                connections.getObject().send(packet1);
            }
            else {
                connections.getObject().send(packet2);
                choosen = connections.getObject();
            }

            connections.next();
        }

        Packet p = choosen.read();
        for (int i = 0; i < questions.length; i++) {
            if (questions[i].getBody().equals(p.getContent())) {
                return questions[i];
            }
        }
        return null;
    }

    /**
     * Asks a question to every player and checks if the answers are right or wrong
     * @param question  The question that is going to be asked to every player
     */
    protected void askQuestion(Question question) {
        Packet packet = new Packet(PacketType.ASK_QUESTION, gson.toJson(question));
        connections.toFirst();
        for (int i = 0; i < connections.size(); i++) {
            connections.getObject().send(packet);
            connections.next();
        }

        HashMap<ClientConnection, Integer> answers = new HashMap<>();

        connections.toFirst();
        for (int i = 0; i < connections.size(); i++) {
            Packet p = connections.getObject().read();
            if (p.getPacketType().equals(PacketType.ANSWER)) {
                answers.put(connections.getObject(), Integer.parseInt(p.getContent()));
                System.out.println(p.getContent());
            }
            connections.next();
        }

        Packet rightPacket = new Packet(PacketType.RIGHT_ANSWER, "");
        Packet wrongPacket = new Packet(PacketType.WRONG_ANSWER, "");
        for (Map.Entry<ClientConnection, Integer> entry : answers.entrySet()) {
            if (entry.getValue().equals(question.getCorrect())) {
                entry.getKey().send(rightPacket);
            }
            else {
                entry.getKey().send(wrongPacket);
                entry.getKey().setFailed(true);
            }
        }
    }
}