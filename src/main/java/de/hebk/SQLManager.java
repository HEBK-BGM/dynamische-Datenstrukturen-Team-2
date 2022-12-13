package de.hebk;

import de.hebk.model.list.List;

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

        try (PreparedStatement stmt = conn.prepareStatement("SELECT * FROM questions;")) {
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
