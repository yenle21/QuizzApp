/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lby.exam;

import com.lby.pojo.Question;
import com.lby.services.questions.BaseQuestionServices;
import com.lby.services.questions.LimitQuestionDecorator;
import com.lby.utils.Configs;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author admin
 */
public class SpecificExamStratery extends ExamStrategy{
    private int num;

    public SpecificExamStratery(int num) {
        this.num = num;
    }
    

    @Override
    public List<Question> getQuestions() throws SQLException {
        BaseQuestionServices s = new LimitQuestionDecorator(Configs.questionServices, this.num);
        return s.list();
    }
    
}
