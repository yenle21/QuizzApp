/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lby.utils;

import com.lby.services.CategoryService;
import com.lby.services.LevelService;
import com.lby.services.questions.BaseQuestionServices;
import com.lby.services.questions.QuestionServices;
import com.lby.services.questions.UpdateQuestionServices;

/**
 *
 * @author admin
 */
public class Configs {

    public static final LevelService levelService = new LevelService();
    public static final BaseQuestionServices questionServices = new QuestionServices();
    public static final CategoryService cateService = new CategoryService();
    public static final UpdateQuestionServices upServices = new UpdateQuestionServices();

    public static final int NUM_OF_QUES = 10;
    public static final double[] RATES = {0.4,0.4,0.2};

}
