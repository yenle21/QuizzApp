/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lby.utils.theme;

import com.lby.quizzapp.App;

/**
 *
 * @author admin
 */
public class DefaultThemeFactory implements ThemeFactory{

    @Override
    public String getStyleSheet() {
        return App.class.getResource("Styles.css").toExternalForm();
    }
    
}
