/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lby.services.questions;


import java.util.List;

/**
 *
 * @author Bao Yen
 */
public class QuestionServices  extends BaseQuestionServices{


    @Override
    public String getSQL(List<Object> params) {
        return "SELECT * FROM question Where 1=1";
    }
}
