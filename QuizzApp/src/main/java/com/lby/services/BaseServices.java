/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lby.services;


import com.lby.utils.JdbcConnector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.List;

/**
 *
 * @author admin
 */
public abstract class BaseServices<T> {
    public abstract PreparedStatement getStatement(Connection conn) throws SQLException ;
    public abstract List<T> getResult(ResultSet rs ) throws SQLException ;
    public List<T> list() throws SQLException{
        Connection conn = JdbcConnector.getInstance().connect();
        PreparedStatement stm = this.getStatement(conn);
        return this.getResult(stm.executeQuery());
       
    }
    
    
}
