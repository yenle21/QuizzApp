/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lby.services;

import com.lby.pojo.Category;
import com.lby.pojo.Choice;
import com.lby.pojo.Question;
import com.lby.utils.JdbcConnector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Bao Yen
 */
public class QuestionServices {

    public void addQuestion(Question q) throws SQLException {
        Connection conn = JdbcConnector.getInstance().connect();

        conn.setAutoCommit(false);
        String sql = "INSERT INTO question(content, hint, image, category_id, level_id) VALUES(?, ?, ?, ?, ?)";
        PreparedStatement stm = conn.prepareCall(sql);
        stm.setString(1, q.getContent());
        stm.setString(2, q.getHint());
        stm.setString(3, q.getImage());
        stm.setInt(4, q.getCategory().getId());
        stm.setInt(5, q.getLevel().getId());

        if (stm.executeUpdate() > 0) {
            int qId = -1;
            ResultSet r = stm.getGeneratedKeys();
            if (r.next()) {
                qId = r.getInt(1);
            }

            sql = "INSERT INTO choice(content, is_correct, question_id) VALUES(?, ?, ?)";

            for (var c : q.getChoices()) {
                stm = conn.prepareCall(sql);
                stm.setString(1, c.getContent());
                stm.setBoolean(2, c.isCorrect());
                stm.setInt(3, qId);

                stm.executeUpdate();
            }

            conn.commit();
        } else {
            conn.rollback();
        }
    }

    public List<Question> getQuestions() throws SQLException {
        Connection conn = JdbcConnector.getInstance().connect();
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery("SELECT * FROM question");
        List<Question> questions = new ArrayList<>();
        while (rs.next()) {
            int id = rs.getInt("id");
            String content = rs.getString("content");
            Question q = new Question.Builder(id, content).build();
            questions.add(q);
        }
        return questions;
    }

    public List<Question> getQuestions(String kw) throws SQLException {
        Connection conn = JdbcConnector.getInstance().connect();
        PreparedStatement stm = conn.prepareCall("SELECT * FROM question WHERE content like concat('%',?,'%')");
        stm.setString(1, kw);
        ResultSet rs = stm.executeQuery();
        List<Question> questions = new ArrayList<>();
        while (rs.next()) {
            int id = rs.getInt("id");
            String content = rs.getString("content");
            Question q = new Question.Builder(id, content).build();
            questions.add(q);
        }
        return questions;
    }
     public List<Question> getQuestions(int num) throws SQLException {
        Connection conn = JdbcConnector.getInstance().connect();
        PreparedStatement stm = conn.prepareCall("SELECT * FROM question ORDER BY rand()  LIMIT ?");
        stm.setInt(1, num);
        ResultSet rs = stm.executeQuery();
        List<Question> questions = new ArrayList<>();
        while (rs.next()) {
            int id = rs.getInt("id");
            String content = rs.getString("content");
            Question q = new Question.Builder(id, content).addAllChoice(this.getChoiceByQuestionId(id)).build();
            questions.add(q);
        }
        return questions;
    }
     public List<Choice> getChoiceByQuestionId(int questionId) throws SQLException{
          Connection conn = JdbcConnector.getInstance().connect();
        PreparedStatement stm = conn.prepareCall("SELECT * FROM choice WHERE question_id=?");
        stm.setInt(1, questionId);
        ResultSet rs = stm.executeQuery();
        List<Choice> choices = new ArrayList<>();
        while (rs.next()) {
            int id = rs.getInt("id");
            String content = rs.getString("content");
            boolean correct = rs.getBoolean("is_correct");
           
            choices.add(new Choice(id, content, correct));
        }
        return choices;
     }
    public boolean deleteQuestions(int questionId) throws SQLException{
         Connection conn = JdbcConnector.getInstance().connect();
        PreparedStatement stm = conn.prepareCall("Delete FROM question WHERE id=?");
        stm.setInt(1, questionId);
        return stm.executeUpdate()>0;
    }
}
