/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lby.services;


import com.lby.pojo.Level;
import com.lby.utils.JdbcConnector;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author admin
 */
public class LevelService {

    public List<Level> getLevels() throws SQLException {
        Connection conn = JdbcConnector.getInstance().connect();
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery("SELECT * FROM level");
        List<Level> levels = new ArrayList<>();
        while (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            String note = rs.getString("note");
            Level level = new Level(id, name, note);
            levels.add(level);
        }
        return levels;
    }


}
