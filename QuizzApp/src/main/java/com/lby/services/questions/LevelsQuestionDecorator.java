/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lby.services.questions;

import com.lby.pojo.Level;
import java.util.List;

/**
 *
 * @author admin
 */
public class LevelsQuestionDecorator extends QuestionsDecorator{
   private Level lv;

    public LevelsQuestionDecorator( BaseQuestionServices decorator,Level lv) {
        super(decorator);
        this.lv = lv;
    }

    public LevelsQuestionDecorator( BaseQuestionServices decorator, int id) {
        super(decorator);
        this.lv = new Level(id);
    }
    

    @Override
    public String getSQL(List<Object> params) {
       String sql = this.decorator.getSQL(params) + " AND Level_id=?";
       params.add(this.lv.getId());
       return sql;
    }
   
    
}
