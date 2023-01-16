package de.hebk.gamemodes.mutliplayer;

import com.google.gson.Gson;
import de.hebk.game.Question;
import de.hebk.game.SQLManager;
import de.hebk.model.list.List;
import de.hebk.multiplayer.ClientConnection;
import de.hebk.multiplayer.Packet;
import de.hebk.multiplayer.PacketType;
import de.hebk.multiplayer.Server;

import java.util.HashMap;

abstract class MultiplayerGamemode {
    private List<ClientConnection> connections;
    private Server server;
    protected Gson gson;
    protected SQLManager sqlManager;
    private boolean keepPlaying = false;

    /**
     * Contructor for a mutliplayer gamemode
     * @param connections   Every connection to the clients
     * @param sqlManager    SQLManager
     * @param server        The server the game is running on
     */
    public MultiplayerGamemode(List<ClientConnection> connections, SQLManager sqlManager, Server server) {
        this.connections = connections;
        this.gson = new Gson();
        this.sqlManager = sqlManager;
        this.server = server;
    }

    public abstract void startGame();

    /**
     * Picks a random player and the player picks a question
     * @param questions Questions the player can choose
     * @return      Question the player has selected
     */
    protected Question selectPlayerQuestion(Question[] questions) {
        String[] questionString = new String[4];
        for (int i = 0; i < questions.length; i++) {
            questionString[i] = questions[i].getBody();
        }

        ClientConnection choosen = getRandomPlayer();

        if (choosen != null) {
            Packet packet1 = new Packet(PacketType.QUESTION_IS_SELECTED, choosen.getUsername());
            Packet packet2 = new Packet(PacketType.SELECT_QUESTION, gson.toJson(questionString));

            connections.toFirst();
            for (int i = 0; i < connections.size(); i++) {
                if (connections.getObject().equals(choosen)) {
                    connections.getObject().send(packet2);
                }
                else if (!connections.getObject().hasFailed()) {
                    connections.getObject().send(packet1);
                }

                connections.next();
            }

            Packet p = choosen.read();
            for (int i = 0; i < questions.length; i++) {
                if (questions[i].getBody().equals(p.getContent())) {
                    return questions[i];
                }
            }
        }
        return null;
    }

    protected ClientConnection getRandomPlayer() {
        int random = (int) (Math.random() * connections.size());

        if (countAlivePlayers() > 0) {
            connections.toFirst();
            for (int i = 0; i < connections.size(); i++) {
                if (i == random) {
                    if (!connections.getObject().hasFailed()) {
                        return connections.getObject();
                    }
                    else {
                        return getRandomPlayer();
                    }
                }
                connections.next();
            }
        }

        return null;
    }

    /**
     * Asks a question to every player
     * @param question  The question that is going to be asked to every player
     */
    protected void askQuestion(Question question) {
        Packet packet = new Packet(PacketType.ASK_QUESTION, gson.toJson(question));
        connections.toFirst();
        for (int i = 0; i < connections.size(); i++) {
            if (!connections.getObject().hasFailed()) {
                connections.getObject().send(packet);
            }
            connections.next();
        }
    }

    /**
     * Returns the answers of the players
     * @return  HashMap with the ClientConnection and answer
     */
    protected HashMap<ClientConnection, String> getAnswers() {
        HashMap<ClientConnection, String> answers = new HashMap<>();

        connections.toFirst();
        for (int i = 0; i < connections.size(); i++) {
            if (!connections.getObject().hasFailed()) {
                Packet p = connections.getObject().read();
                if (p.getPacketType().equals(PacketType.ANSWER)) {
                    answers.put(connections.getObject(), p.getContent());
                }
            }
            connections.next();
        }

        return answers;
    }

    /**
     * Gets the amount of players who still can play
     * @return  The amount of players
     */
    protected int countAlivePlayers() {
        int counter = 0;

        connections.toFirst();
        for (int i = 0; i < connections.size(); i++) {
            if (!connections.getObject().hasFailed()) {
                counter++;
            }
            connections.next();
        }

        return counter;
    }

    /**
     * Gets all connections in a list
     * @return  All connections
     */
    protected List<ClientConnection> getConnections() {
        return this.connections;
    }

    /**
     * Ends the game
     * @param finallevel    The level the players were able to play to
     */
    protected void endGame(int finallevel) {
        Packet endPacket = new Packet(PacketType.END, finallevel + "");

        getConnections().toFirst();
        for (int i = 0; i < getConnections().size(); i++) {
            getConnections().getObject().send(endPacket);
            getConnections().next();
        }

        server.stopServer();
    }

    /**
     * Returns if the last player wants to keep playing
     * @return  Boolean
     */
    protected boolean isKeepPlaying() {
        return this.keepPlaying;
    }

    /**
     * Sets if the last player wants to keep playing
     * @param bool Boolean
     */
    protected void setKeepPlaying(boolean bool) {
        this.keepPlaying = bool;
    }

    /**
     * Checks how many players still can play and decides to keep playing or stop the game
     * @param level     The current level
     * @param maxlevel  The max level possible
     */
    protected void checkGameStatus(int level, int maxlevel) {
        // checks how many players still can play and decides to keep playing or stop the game
        int alive = countAlivePlayers();
        if (alive == 1 && !isKeepPlaying()) {
            Packet packet = new Packet(PacketType.LAST_ALIVE, "");

            getConnections().toFirst();
            for (int j = 0; j < getConnections().size(); j++) {
                if (!getConnections().getObject().hasFailed()) {
                    getConnections().getObject().send(packet);

                    Packet p = getConnections().getObject().read();
                    if (p.getPacketType().equals(PacketType.STOP_PLAYING)) {
                        endGame(level);
                        return;
                    }
                    else {
                        setKeepPlaying(true);
                    }
                    break;
                }
                getConnections().next();
            }
        }
        else if (alive == 0) {
            endGame(level);
            return;
        }

        if (level == maxlevel) {
            endGame(level);
        }
    }
}