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
public abstract class QuestionsDecorator extends BaseQuestionServices{
    protected BaseQuestionServices decorator;

    public QuestionsDecorator(BaseQuestionServices decorator) {
        this.decorator = decorator;
    }
    
    
    
}
