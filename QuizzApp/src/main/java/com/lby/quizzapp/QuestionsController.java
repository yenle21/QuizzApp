/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.lby.quizzapp;

import com.lby.pojo.Category;
import com.lby.pojo.Level;
import com.lby.pojo.Question;
import com.lby.services.CategoryService;
import com.lby.services.LevelService;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author admin
 */
public class QuestionsController implements Initializable {

    @FXML private ComboBox<Category> cbCates;
    @FXML private ComboBox<Level> cbLevels;
    
    @FXML private VBox vboxChoice;
    @FXML private TextArea txtContent;
    private final static CategoryService cateService = new CategoryService();
      private final static LevelService levelService = new LevelService();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            this.cbCates.setItems(FXCollections.observableList(cateService.getCates()));
            this.cbLevels.setItems(FXCollections.observableList(levelService.getLevels()));
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    public void addChoice(ActionEvent event){
        HBox h = new HBox();
        h.getStyleClass().add("Main");
        RadioButton rd = new RadioButton();
        TextField txt = new TextField();
        h.getChildren().addAll(rd,txt);
        this.vboxChoice.getChildren().add(h);
    }
    public void addQuestion (ActionEvent event)
    {
        Question.Builder b = new Question.Builder(this.txtContent.getText(),
                this.cbCates.getSelectionModel().getSelectedItem(), 
                 this.cbLevels.getSelectionModel().getSelectedItem()
        );
    }

}
