/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lby.services.questions;

import java.util.List;



/**
 *
 * @author admin
 */
public  class KeyWordQuestionDecorator extends QuestionsDecorator{
  
    private String keyword;

    public KeyWordQuestionDecorator( BaseQuestionServices decorator,String keyword) {
        super(decorator);
        this.keyword = keyword;
    }

    @Override
    public String getSQL(List<Object> params) {
        String sql = this.decorator.getSQL(params) + " AND content like concat('%',?,'%')";
        params.add(this.keyword);
        return sql;
    }
       
    

 

   

    

}
