package de.hebk;

import de.hebk.game.Question;
import de.hebk.model.list.List;
import de.hebk.model.queue.Queue;
import java.sql.*;

public class SQLManager {

    private Connection conn;

    public SQLManager(String database) {
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:" + database);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Question> getQuestions() {
        List<Question> list = new List<>();

        try (PreparedStatement stmt = conn.prepareStatement("SELECT * FROM questions ORDER BY level ASC;")) {
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

    public Question getRandomQuestions() {
        try (PreparedStatement stmt = conn.prepareStatement("SELECT * FROM questions ORDER BY RANDOM() LIMIT 1;")) {
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

    public Queue<Question> getQueueQuestions() {
        Queue<Question> queue = new Queue<>();

        try (PreparedStatement stmt = conn.prepareStatement("SELECT * FROM questions ORDER BY level ASC;")) {
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

    public List<Question> getQuestionsFromLevel(int level) {
        List<Question> list = new List<>();

        try (PreparedStatement stmt = conn.prepareStatement("SELECT * FROM questions WHERE level = ?;")) {
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

    public List<Question> getRandomQuestionsFromLevel(int level, int limit) {
        List<Question> list = new List<>();

        try (PreparedStatement stmt = conn.prepareStatement("SELECT * FROM questions WHERE level = ? ORDER BY RANDOM() LIMIT ?;")) {
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

            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void addQuestion(Question question) {
        try (PreparedStatement stmt = conn.prepareStatement("INSERT INTO question (body, a, b, c, d, correct, level) VALUES (?, ?, ?, ?, ?, ?, ?);")) {
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
}
