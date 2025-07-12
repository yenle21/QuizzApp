/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lby.services.questions;

import com.lby.pojo.Category;
import java.util.List;

/**
 *
 * @author admin
 */
public class CategoryQuestionsDecorator extends QuestionsDecorator{
    private Category cate;

    public CategoryQuestionsDecorator( BaseQuestionServices decorator,Category cate) {
        super(decorator);
        this.cate = cate;
    }

    public CategoryQuestionsDecorator( BaseQuestionServices decorator, int id) {
        super(decorator);
        this.cate = new Category(id);
    }
    

    @Override
    public String getSQL(List<Object> params) {
        String sql = this.decorator.getSQL(params) + " AND category_id=?";
        params.add(this.cate.getId());
        return sql;
    }
    
}
