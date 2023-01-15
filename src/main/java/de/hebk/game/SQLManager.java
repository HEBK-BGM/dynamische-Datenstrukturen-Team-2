package de.hebk.game;

import de.hebk.game.Question;
import de.hebk.model.list.List;
import de.hebk.model.queue.Queue;
import de.hebk.model.stack.Stack;

import java.io.File;
import java.io.IOException;
import java.sql.*;

public class SQLManager {

    private Connection questionConn;
    private Connection highscoreConn;

    /**
     * Constructor for the SQLManager
     * @param database File path to the database
     */
    public SQLManager(String database) {
        try {
            File file = new File("C:\\Users\\" + System.getProperty("user.name") + "\\Documents\\Wer wird Millionär\\");
            if (!file.exists()) {
                file.mkdirs();
            }

            questionConn = DriverManager.getConnection("jdbc:sqlite:" + database);
            highscoreConn = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\" + System.getProperty("user.name") + "\\Documents\\Wer wird Millionär\\wwm.db");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try (PreparedStatement stmt = highscoreConn.prepareStatement("CREATE TABLE IF NOT EXISTS highscores (name TEXT, gamemode TEXT, level int, money int, date TEXT)")) {
            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Gets every question in a List
     * @return  Every question in a List
     */
    public List<Question> getQuestions() {
        List<Question> list = new List<>();

        try (PreparedStatement stmt = questionConn.prepareStatement("SELECT * FROM questions ORDER BY level ASC;")) {
            ResultSet rs = stmt.executeQuery();

            Question question = null;
            while (rs.next()) {
                String[] answers = new String[4];
                answers[0] = rs.getString("a");
                answers[1] = rs.getString("b");
                answers[2] = rs.getString("c");
                answers[3] = rs.getString("d");
                question = new Question(rs.getString("body"), rs.getInt("level"), answers, rs.getInt("correct"));

                list.insert(question);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return list;
    }

    /**
     * Gets one random question
     * @return  Random question
     */
    public Question getRandomQuestions() {
        try (PreparedStatement stmt = questionConn.prepareStatement("SELECT * FROM questions ORDER BY RANDOM() LIMIT 1;")) {
            ResultSet rs = stmt.executeQuery();

            String[] answers = new String[4];
            answers[0] = rs.getString("a");
            answers[1] = rs.getString("b");
            answers[2] = rs.getString("c");
            answers[3] = rs.getString("d");

            return new Question(rs.getString("body"), rs.getInt("level"), answers, rs.getInt("correct"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Gets every question ordered by level ascending in a queue
     * @return  Every question ascending in a queue
     */
    public Queue<Question> getQueueQuestions() {
        Queue<Question> queue = new Queue<>();

        try (PreparedStatement stmt = questionConn.prepareStatement("SELECT * FROM questions ORDER BY level ASC;")) {
            ResultSet rs = stmt.executeQuery();

            Question question = null;
            while (rs.next()) {
                String[] answers = new String[4];
                answers[0] = rs.getString("a");
                answers[1] = rs.getString("b");
                answers[2] = rs.getString("c");
                answers[3] = rs.getString("d");
                question = new Question(rs.getString("body"), rs.getInt("level"), answers, rs.getInt("correct"));

                queue.enqueue(question);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return queue;
    }

    /**
     * Gets every question from a level
     * @param level The given level
     * @return      The questions in a list
     */
    public List<Question> getQuestionsFromLevel(int level) {
        List<Question> list = new List<>();

        try (PreparedStatement stmt = questionConn.prepareStatement("SELECT * FROM questions WHERE level = ?;")) {
            stmt.setInt(1, level);
            ResultSet rs = stmt.executeQuery();

            Question question = null;
            while (rs.next()) {
                String[] answers = new String[4];
                answers[0] = rs.getString("a");
                answers[1] = rs.getString("b");
                answers[2] = rs.getString("c");
                answers[3] = rs.getString("d");
                question = new Question(rs.getString("body"), rs.getInt("level"), answers, rs.getInt("correct"));

                list.insert(question);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return list;
    }

    /**
     * Gets random questions from a level with a limit
     * @param level The given level
     * @param limit The given limit
     * @return      List with the random questions
     */
    public List<Question> getRandomQuestionsFromLevel(int level, int limit) {
        List<Question> list = new List<>();

        try (PreparedStatement stmt = questionConn.prepareStatement("SELECT * FROM questions WHERE level = ? ORDER BY RANDOM() LIMIT ?;")) {
            stmt.setInt(1, level);
            stmt.setInt(2, limit);
            ResultSet rs = stmt.executeQuery();

            Question question = null;
            while (rs.next()) {
                String[] answers = new String[4];
                answers[0] = rs.getString("a");
                answers[1] = rs.getString("b");
                answers[2] = rs.getString("c");
                answers[3] = rs.getString("d");
                question = new Question(rs.getString("body"), rs.getInt("level"), answers, rs.getInt("correct"));

                list.insert(question);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return list;
    }

    /**
     * Adds a question
     * @param question  The given question
     */
    public void addQuestion(Question question) {
        try (PreparedStatement stmt = questionConn.prepareStatement("INSERT INTO question (body, a, b, c, d, correct, level) VALUES (?, ?, ?, ?, ?, ?, ?);")) {
            stmt.setString(1, question.getBody());
            stmt.setString(2, question.getAnswers()[0]);
            stmt.setString(3, question.getAnswers()[1]);
            stmt.setString(4, question.getAnswers()[2]);
            stmt.setString(5, question.getAnswers()[3]);
            stmt.setInt(6, question.getCorrect());
            stmt.setInt(7, question.getLevel());
            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Returns every highscore in a stack
     * @return  Every highscore in a stack
     */
    public Stack<Highscore> getHighscores() {
        try (PreparedStatement stmt = highscoreConn.prepareStatement("SELECT * FROM highscores ORDER BY money ASC;")) {
            ResultSet rs = stmt.executeQuery();

            Stack<Highscore> highscores = new Stack<>();

            while (rs.next()) {
                String name = rs.getString("name");
                String gamemode = rs.getString("gamemode");
                int level = rs.getInt("level");
                int money = rs.getInt("money");
                String date = rs.getString("date");

                Highscore h = new Highscore(name, gamemode, level, money, date);
                highscores.push(h);
            }

            return highscores;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Adds a highscore
     * @param highscore A highscore
     */
    public void addHighscore(Highscore highscore) {
        try (PreparedStatement stmt = highscoreConn.prepareStatement("INSERT INTO highscores (name, gamemode, level, money, date) VALUES (?, ?, ?, ?, ?)")) {
            stmt.setString(1, highscore.getName());
            stmt.setString(2, highscore.getGamemode());
            stmt.setInt(3, highscore.getLevel());
            stmt.setInt(4, highscore.getMoney());
            stmt.setString(5, highscore.getDate());
            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
